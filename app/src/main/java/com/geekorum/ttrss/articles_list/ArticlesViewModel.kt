/*
 * Geekttrss is a RSS feed reader application on the Android Platform.
 *
 * Copyright (C) 2017-2025 by Frederic-Charles Barthelery.
 *
 * This file is part of Geekttrss.
 *
 * Geekttrss is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Geekttrss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Geekttrss.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.geekorum.ttrss.articles_list

import android.accounts.Account
import androidx.core.text.parseAsHtml
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.geekorum.ttrss.background_job.BackgroundJobManager
import com.geekorum.ttrss.background_job.RefreshFeedInfo
import com.geekorum.ttrss.data.ArticleWithFeed
import com.geekorum.ttrss.data.Feed
import com.geekorum.ttrss.session.Action
import com.geekorum.ttrss.session.SessionActivityComponent
import com.geekorum.ttrss.session.UndoManager
import com.geekorum.ttrss.sync.SyncProgressTracker
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Base [ViewModel] for a list of Articles.
 *
 * The article list is exposed as a plain [StateFlow] of [List] backed directly by a Room
 * `Flow<List<...>>` query. `articleCount` is derived from `articles.size` so list and count
 * share a single source of truth and can never disagree. No Paging 3 / `cachedIn` layer is
 * used here — Room's own invalidation tracker refreshes the flow on every write.
 */
abstract class BaseArticlesViewModel(
    componentFactory: SessionActivityComponent.Factory
) : ViewModel() {

    protected val component = componentFactory.newComponent()
    protected val articlesRepository = component.articleRepository
    private val setFieldActionFactory = component.setArticleFieldActionFactory

    abstract val articles: StateFlow<List<ArticleWithFeed>>

    private val _pendingArticlesSetUnread = MutableStateFlow(0)
    val pendingArticlesSetUnread = _pendingArticlesSetUnread.asStateFlow()

    abstract val isRefreshing: StateFlow<Boolean>
    abstract val isMultiFeed: StateFlow<Boolean>
    abstract val isAllArticlesFeed: StateFlow<Boolean>

    /** Derived from [articles] — always equals the rendered list size. */
    val articleCount: StateFlow<Int> by lazy {
        articles.map { it.size }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    }

    private val unreadActionUndoManager = UndoManager<Action>()

    abstract fun refresh()

    protected val sortByMostRecentFirst = MutableStateFlow(false)
    protected val needUnread = MutableStateFlow(false)

    fun setSortByMostRecentFirst(mostRecentFirst: Boolean) {
        sortByMostRecentFirst.value = mostRecentFirst
    }

    fun setNeedUnread(needUnread: Boolean) {
        this.needUnread.value = needUnread
    }

    fun setArticleUnread(articleId: Long, newValue: Boolean) {
        setFieldActionFactory.createSetUnreadAction(viewModelScope, articleId, newValue).also {
            it.execute()
            unreadActionUndoManager.recordAction(it)
        }
        _pendingArticlesSetUnread.value = unreadActionUndoManager.nbActions
    }

    fun setArticleStarred(articleId: Long, newValue: Boolean) {
        val action = setFieldActionFactory.createSetStarredAction(viewModelScope, articleId, newValue)
        action.execute()
    }

    fun commitSetUnreadActions() {
        unreadActionUndoManager.clear()
        _pendingArticlesSetUnread.value = unreadActionUndoManager.nbActions
    }

    fun undoSetUnreadActions() {
        unreadActionUndoManager.undoAll()
        _pendingArticlesSetUnread.value = unreadActionUndoManager.nbActions
    }

    protected fun List<ArticleWithFeed>.withParsedTitles(): List<ArticleWithFeed> =
        map { articleWithFeed ->
            articleWithFeed.copy(
                article = articleWithFeed.article.copy(
                    contentData = articleWithFeed.article.contentData.copy(
                        title = articleWithFeed.article.contentData.title.parseAsHtml().toString()
                    )
                )
            )
        }
}

/**
 * ViewModel for [ArticlesListScreen]
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel(assistedFactory = ArticlesListViewModel.Factory::class)
class ArticlesListViewModel @AssistedInject constructor(
    @Assisted val feedId: Long,
    feedsRepository: FeedsRepository,
    private val backgroundJobManager: BackgroundJobManager,
    private val syncProgressTracker: SyncProgressTracker,
    componentFactory: SessionActivityComponent.Factory
) : BaseArticlesViewModel(componentFactory) {

    @AssistedFactory
    interface Factory {
        fun create(feedId: Long): ArticlesListViewModel
    }

    override val isMultiFeed: StateFlow<Boolean> = MutableStateFlow(Feed.isVirtualFeed(feedId))

    override val isAllArticlesFeed: StateFlow<Boolean> = MutableStateFlow(feedId == Feed.FEED_ID_ALL_ARTICLES)

    override val articles: StateFlow<List<ArticleWithFeed>> =
        combine(
            feedsRepository.getFeedById(feedId).filterNotNull(),
            sortByMostRecentFirst,
            articlesRepository.freshTimeSec
        ) { feed, mostRecentFirst, freshTime ->
            Triple(feed, mostRecentFirst, freshTime)
        }.flatMapLatest { (feed, mostRecentFirst, freshTime) ->
            articleListFlow(feed, mostRecentFirst, freshTime)
        }
            .map { it.withParsedTitles() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private fun articleListFlow(
        feed: Feed, mostRecentFirst: Boolean, freshTime: Long
    ): Flow<List<ArticleWithFeed>> = when (feed.id) {
        Feed.FEED_ID_STARRED ->
            if (mostRecentFirst) articlesRepository.getAllStarredArticles()
            else articlesRepository.getAllStarredArticlesOldestFirst()
        Feed.FEED_ID_PUBLISHED ->
            if (mostRecentFirst) articlesRepository.getAllPublishedArticles()
            else articlesRepository.getAllPublishedArticlesOldestFirst()
        Feed.FEED_ID_ALL_ARTICLES ->
            if (mostRecentFirst) articlesRepository.getAllArticles()
            else articlesRepository.getAllArticlesOldestFirst()
        Feed.FEED_ID_FRESH ->
            if (mostRecentFirst) articlesRepository.getAllUnreadArticlesUpdatedAfterTime(freshTime)
            else articlesRepository.getAllUnreadArticlesUpdatedAfterTimeOldestFirst(freshTime)
        else ->
            if (mostRecentFirst) articlesRepository.getAllUnreadArticlesForFeed(feed.id)
            else articlesRepository.getAllUnreadArticlesForFeedOldestFirst(feed.id)
    }

    private val refreshFeedInfo = MutableStateFlow<RefreshFeedInfo?>(null)

    private val account = component.account

    override val isRefreshing = refreshFeedInfo.flatMapLatest {
        if (it == null)
            syncProgressTracker.isCollectingArticles
        else
            backgroundJobManager.isRefreshingByWorkerId(it.collectArticlesWorkerId).asFlow()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    override fun refresh() {
        if (isRefreshing.value) return
        viewModelScope.launch {
            if (Feed.isVirtualFeed(feedId)) {
                backgroundJobManager.refresh(account)
            } else {
                refreshFeedInfo.value = backgroundJobManager.refreshFeed(account, feedId)
            }
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel(assistedFactory = ArticlesListByTagViewModel.Factory::class)
class ArticlesListByTagViewModel @AssistedInject constructor(
    @Assisted val tag: String,
    private val backgroundJobManager: BackgroundJobManager,
    private val syncProgressTracker: SyncProgressTracker,
    componentFactory: SessionActivityComponent.Factory
) : BaseArticlesViewModel(componentFactory) {

    @AssistedFactory
    interface Factory {
        fun create(tag: String): ArticlesListByTagViewModel
    }

    override val isMultiFeed: StateFlow<Boolean> = MutableStateFlow(true)

    override val isAllArticlesFeed: StateFlow<Boolean> = MutableStateFlow(false)

    private val account: Account = component.account

    override val articles: StateFlow<List<ArticleWithFeed>> =
        combine(sortByMostRecentFirst, needUnread) { mostRecentFirst, needUnread ->
            mostRecentFirst to needUnread
        }.flatMapLatest { (mostRecentFirst, needUnread) ->
            when {
                needUnread && mostRecentFirst -> articlesRepository.getAllUnreadArticlesForTag(tag)
                needUnread && !mostRecentFirst -> articlesRepository.getAllUnreadArticlesForTagOldestFirst(tag)
                !needUnread && mostRecentFirst -> articlesRepository.getAllArticlesForTag(tag)
                else -> articlesRepository.getAllArticlesForTagOldestFirst(tag)
            }
        }
            .map { it.withParsedTitles() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    override val isRefreshing = syncProgressTracker.isCollectingArticles
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    override fun refresh() {
        if (isRefreshing.value) return
        backgroundJobManager.refresh(account)
    }
}
