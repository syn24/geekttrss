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

import androidx.paging.PagingSource
import com.geekorum.ttrss.core.CoroutineDispatchersProvider
import com.geekorum.ttrss.data.Article
import com.geekorum.ttrss.data.ArticleDao
import com.geekorum.ttrss.data.ArticleWithFeed
import com.geekorum.ttrss.data.Transaction
import com.geekorum.ttrss.data.TransactionsDao
import com.geekorum.ttrss.network.ApiService
import com.geekorum.ttrss.providers.ArticlesContract
import com.geekorum.ttrss.session.Action
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * A Facade to access and modify Articles.
 */
class ArticlesRepository
@Inject constructor(
    private val articleDao: ArticleDao,
) {

    fun getAllArticles(): Flow<List<ArticleWithFeed>> = articleDao.getAllArticles()
    fun getAllArticlesOldestFirst(): Flow<List<ArticleWithFeed>> = articleDao.getAllArticlesOldestFirst()

    fun getAllUnreadArticles(): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticles()
    fun getAllUnreadArticlesOldestFirst(): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticlesOldestFirst()

    suspend fun getUnreadArticlesRandomized(count: Int): List<ArticleWithFeed> {
        return articleDao.getUnreadArticlesRandomized(count)
    }

    fun getAllPublishedArticles(): Flow<List<ArticleWithFeed>> = articleDao.getAllPublishedArticles()
    fun getAllPublishedArticlesOldestFirst(): Flow<List<ArticleWithFeed>> = articleDao.getAllPublishedArticlesOldestFirst()

    fun getAllUnreadPublishedArticles(): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadPublishedArticles()
    fun getAllUnreadPublishedArticlesOldestFirst(): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadPublishedArticlesOldestFirst()

    fun getAllStarredArticles(): Flow<List<ArticleWithFeed>> = articleDao.getAllStarredArticles()
    fun getAllStarredArticlesOldestFirst(): Flow<List<ArticleWithFeed>> = articleDao.getAllStarredArticlesOldestFirst()

    fun getAllUnreadStarredArticles(): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadStarredArticles()
    fun getAllUnreadStarredArticlesOldestFirst(): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadStarredArticlesOldestFirst()

    fun getAllArticlesForFeed(feedId: Long): Flow<List<ArticleWithFeed>> = articleDao.getAllArticlesForFeed(feedId)
    fun getAllArticlesForFeedOldestFirst(feedId: Long): Flow<List<ArticleWithFeed>> = articleDao.getAllArticlesForFeedOldestFirst(feedId)

    fun getAllUnreadArticlesForFeed(feedId: Long): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticlesForFeed(feedId)
    fun getAllUnreadArticlesForFeedOldestFirst(feedId: Long): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticlesForFeedOldestFirst(feedId)

    suspend fun getAllUnreadArticlesForFeedUpdatedAfterTimeRandomized(feedId: Long, time: Long): List<Article> {
        return articleDao.getAllUnreadArticlesForFeedUpdatedAfterTimeRandomized(feedId, time)
    }

    fun getAllArticlesForTag(tag: String): Flow<List<ArticleWithFeed>> = articleDao.getAllArticlesForTag(tag)
    fun getAllArticlesForTagOldestFirst(tag: String): Flow<List<ArticleWithFeed>> = articleDao.getAllArticlesForTagOldestFirst(tag)

    fun getAllUnreadArticlesForTag(tag: String): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticlesForTag(tag)
    fun getAllUnreadArticlesForTagOldestFirst(tag: String): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticlesForTagOldestFirst(tag)

    suspend fun getUnreadArticlesForTag(tag: String, count: Int = 3): List<ArticleWithFeed> {
        return articleDao.getUnreadArticlesForTag(tag, count)
    }

    fun getAllUnreadArticlesUpdatedAfterTime(time: Long): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticlesUpdatedAfterTime(time)

    fun getAllUnreadArticlesUpdatedAfterTimeOldestFirst(time: Long): Flow<List<ArticleWithFeed>> = articleDao.getAllUnreadArticlesUpdatedAfterTimeOldestFirst(time)

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getArticleById(articleId: Long): Flow<Article?> = articleDao.getArticleById(articleId).distinctUntilChanged()

    fun getArticlesById(articleIds: List<Long>): PagingSource<Int, ArticleWithFeed> {
        return articleDao.getArticlesById(articleIds)
    }

    suspend fun setArticleUnread(articleId: Long, newValue: Boolean) {
        articleDao.updateArticleUnread(articleId, newValue)
    }

    suspend fun setArticleStarred(articleId: Long, newValue: Boolean) {
        articleDao.updateArticleMarked(articleId, newValue)
    }

    suspend fun setAllArticlesUnread(newValue: Boolean) {
        articleDao.updateAllArticleUnread(newValue)
    }

    suspend fun setArticlesUnreadForFeed(feedId: Long, newValue: Boolean) {
        articleDao.updateArticleUnreadForFeed(feedId, newValue)
    }

    suspend fun setStarredArticlesUnread(newValue: Boolean) {
        articleDao.updateStarredArticlesUnread(newValue)
    }

    suspend fun setPublishedArticlesUnread(newValue: Boolean) {
        articleDao.updatePublishedArticlesUnread(newValue)
    }

    fun searchArticles(query: String): PagingSource<Int, ArticleWithFeed> {
        return articleDao.searchArticles(query)
    }

    suspend fun getMostUnreadTags(count: Int): List<String> {
        return articleDao.getMostUnreadTags(count)
    }

    /**
     * Single source of truth for the "fresh" time cutoff (36 hours ago).
     * Recomputed every 10 minutes. Used by both count and list queries
     * to ensure they always agree on which articles are "fresh".
     */
    val freshTimeSec: Flow<Long> = flow {
        while (true) {
            emit(System.currentTimeMillis() / 1000 - 3600 * 36)
            delay(10 * 60 * 1000L)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getFreshUnreadCount(): Flow<Int> {
        return freshTimeSec.flatMapLatest { time ->
            articleDao.getUnreadArticlesUpdatedAfterTimeCount(time)
        }
    }

    fun getAllArticlesCount(): Flow<Int> {
        return articleDao.getAllArticlesCount()
    }

    fun getAllUnreadArticlesCount(): Flow<Int> {
        return articleDao.getAllUnreadArticlesCount()
    }

    fun getAllStarredArticlesCount(): Flow<Int> {
        return articleDao.getAllStarredArticlesCount()
    }

    fun getUnreadArticlesCountForFeed(feedId: Long): Flow<Int> {
        return articleDao.getUnreadArticlesForFeedCount(feedId)
    }
}


open class SetArticleFieldAction(
    private val dispatchers: CoroutineDispatchersProvider,
    private val scope: CoroutineScope,
    private val transactionsDao: TransactionsDao,
    private val apiService: ApiService,
    private val articleId: Long,
    private val field: ArticlesContract.Transaction.Field,
    private val newValue: Boolean
) : Action {

    private var executionJob: Job? = null

    override fun execute() {
        executionJob = scope.launch(dispatchers.io) {
            updateArticleField(newValue)
        }
    }

    override fun undo() {
        scope.launch(dispatchers.io) {
            // wait for the request to be cancelled and done
            // to be sure that the new one happens after
            executionJob?.cancelAndJoin()
            updateArticleField(!newValue)
        }
    }

    protected open suspend fun updateArticleField(value: Boolean) {
        try {
            apiService.updateArticleField(articleId, field, value)
        } catch (e: Exception) {
            saveTransaction(articleId, field, value)
        }
    }

    private fun saveTransaction(articleId: Long, field: ArticlesContract.Transaction.Field, value: Boolean) {
        val transaction = Transaction(articleId = articleId,
            field = field.toString(),
            value = value)
        transactionsDao.insertUniqueTransaction(transaction)
    }

    class Factory @Inject internal constructor(
        private val setUnreadActionFactory: SetUnreadAction.Factory,
        private val setStarredActionFactory: SetStarredAction.Factory,
        private val setPublishedActionFactory: SetPublishedAction.Factory
    ) : SetUnreadAction.Factory by setUnreadActionFactory, SetStarredAction.Factory by setStarredActionFactory,
            SetPublishedAction.Factory by setPublishedActionFactory

}


/**
 * Action to set the unread field value of an article.
 */
internal class SetUnreadAction @AssistedInject internal constructor(
    dispatchers: CoroutineDispatchersProvider,
    private val articleDao: ArticleDao,
    transactionsDao: TransactionsDao,
    apiService: ApiService,
    @Assisted private val articleId: Long,
    @Assisted newValue: Boolean,
    @Assisted scope: CoroutineScope,
) : SetArticleFieldAction(dispatchers, scope, transactionsDao, apiService, articleId,
    ArticlesContract.Transaction.Field.UNREAD, newValue) {

    override suspend fun updateArticleField(value: Boolean) {
        val changed = articleDao.updateArticleUnread(articleId, value)
        if (changed > 0) {
            super.updateArticleField(value)
        }
    }

    @AssistedFactory
    interface Factory {
        fun createSetUnreadAction(scope: CoroutineScope, articleId: Long, newValue: Boolean): SetUnreadAction
    }
}

/**
 * Action to set the starred field value of an article.
 */
internal class SetStarredAction @AssistedInject internal constructor(
     dispatchers: CoroutineDispatchersProvider,
    private val articleDao: ArticleDao,
    transactionsDao: TransactionsDao,
    val apiService: ApiService,
    @Assisted val articleId: Long,
    @Assisted newValue: Boolean,
     @Assisted scope: CoroutineScope
) : SetArticleFieldAction(dispatchers, scope, transactionsDao, apiService, articleId,
        ArticlesContract.Transaction.Field.STARRED, newValue) {

    override suspend fun updateArticleField(value: Boolean) {
        val changed = articleDao.updateArticleMarked(articleId, value)
        if (changed > 0) {
            super.updateArticleField(value)
        }
    }

    @AssistedFactory
    interface Factory {
        fun createSetStarredAction(scope: CoroutineScope, articleId: Long, newValue: Boolean): SetStarredAction
    }

}

/**
 * Action to set the published field value of an article.
 */
internal class SetPublishedAction @AssistedInject internal constructor(
    dispatchers: CoroutineDispatchersProvider,
    private val articleDao: ArticleDao,
    transactionsDao: TransactionsDao,
    val apiService: ApiService,
    @Assisted val articleId: Long,
    @Assisted newValue: Boolean,
    @Assisted scope: CoroutineScope
) : SetArticleFieldAction(dispatchers, scope, transactionsDao, apiService, articleId,
    ArticlesContract.Transaction.Field.PUBLISHED, newValue) {

    override suspend fun updateArticleField(value: Boolean) {
        val changed = articleDao.updateArticlePublished(articleId, value)
        if (changed > 0) {
            super.updateArticleField(value)
        }
    }

    @AssistedFactory
    interface Factory {
        fun createSetPublishedAction(scope: CoroutineScope, articleId: Long, newValue: Boolean): SetPublishedAction
    }

}

