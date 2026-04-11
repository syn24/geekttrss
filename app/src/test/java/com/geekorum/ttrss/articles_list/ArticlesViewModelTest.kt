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
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.geekorum.ttrss.background_job.BackgroundJobManager
import com.geekorum.ttrss.background_job.RefreshFeedInfo
import com.geekorum.ttrss.data.ArticleContentIndexed
import com.geekorum.ttrss.data.Article
import com.geekorum.ttrss.data.ArticleWithFeed
import com.geekorum.ttrss.data.Feed
import com.geekorum.ttrss.data.FeedWithFavIcon
import com.geekorum.ttrss.session.SessionActivityComponent
import com.geekorum.ttrss.sync.SyncProgressTracker
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.UUID

/**
 * Unit tests for [ArticlesListViewModel] and [ArticlesListByTagViewModel].
 *
 * Uses AndroidJUnit4 → Robolectric so that `parseAsHtml()` on article titles
 * does not crash (the real ViewModel pipes titles through `Html.fromHtml`).
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ArticlesViewModelTest {

    @get:Rule
    val archRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var articlesRepository: ArticlesRepository
    private lateinit var setFieldActionFactory: SetArticleFieldAction.Factory
    private lateinit var component: SessionActivityComponent
    private lateinit var componentFactory: SessionActivityComponent.Factory

    private lateinit var feedsRepository: FeedsRepository
    private lateinit var backgroundJobManager: BackgroundJobManager
    private lateinit var syncProgressTracker: SyncProgressTracker

    private val fakeAccount = Account("fred", "com.test")
    private val isCollectingArticlesFlow = MutableStateFlow(false)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        articlesRepository = mockk(relaxed = true)
        setFieldActionFactory = mockk(relaxed = true)

        component = mockk {
            every { account } returns fakeAccount
            every { articleRepository } returns articlesRepository
            every { setArticleFieldActionFactory } returns setFieldActionFactory
        }
        componentFactory = mockk {
            every { newComponent() } returns component
        }

        feedsRepository = mockk()
        backgroundJobManager = mockk(relaxed = true)
        syncProgressTracker = mockk {
            every { isCollectingArticles } returns isCollectingArticlesFlow
        }

        // fresh time flow — emit a single value and let the combine flow proceed
        every { articlesRepository.freshTimeSec } returns flowOf(1_000L)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun newListViewModel(feedId: Long): ArticlesListViewModel {
        return ArticlesListViewModel(
            feedId = feedId,
            feedsRepository = feedsRepository,
            backgroundJobManager = backgroundJobManager,
            syncProgressTracker = syncProgressTracker,
            componentFactory = componentFactory,
        )
    }

    private fun newByTagViewModel(tag: String): ArticlesListByTagViewModel {
        return ArticlesListByTagViewModel(
            tag = tag,
            backgroundJobManager = backgroundJobManager,
            syncProgressTracker = syncProgressTracker,
            componentFactory = componentFactory,
        )
    }

    private fun sampleArticles(vararg ids: Long): List<ArticleWithFeed> =
        ids.map { id ->
            ArticleWithFeed(
                article = Article(id = id, feedId = 1, contentData = ArticleContentIndexed(title = "t$id")),
                feed = FeedWithFavIcon(feed = Feed(id = 1), favIcon = null)
            )
        }

    // --- Virtual feed routing ---

    @Test
    fun starredFeedUsesStarredRepositoryCall() = runTest {
        val expected = sampleArticles(1, 2)
        every { feedsRepository.getFeedById(Feed.FEED_ID_STARRED) } returns
            flowOf(Feed(id = Feed.FEED_ID_STARRED))
        every { articlesRepository.getAllStarredArticles() } returns flowOf(expected)

        val vm = newListViewModel(Feed.FEED_ID_STARRED)
        vm.setSortByMostRecentFirst(true)

        vm.articles.test {
            // initial empty emission, then real value
            assertThat(awaitItem()).isEmpty()
            assertThat(awaitItem().map { it.article.id }).containsExactly(1L, 2L).inOrder()
            cancelAndIgnoreRemainingEvents()
        }
        verify { articlesRepository.getAllStarredArticles() }
    }

    @Test
    fun publishedFeedUsesPublishedRepositoryCall() = runTest {
        every { feedsRepository.getFeedById(Feed.FEED_ID_PUBLISHED) } returns
            flowOf(Feed(id = Feed.FEED_ID_PUBLISHED))
        every { articlesRepository.getAllPublishedArticles() } returns flowOf(sampleArticles(3))

        val vm = newListViewModel(Feed.FEED_ID_PUBLISHED)
        vm.setSortByMostRecentFirst(true)

        vm.articles.test {
            awaitItem() // initial empty
            assertThat(awaitItem()).hasSize(1)
            cancelAndIgnoreRemainingEvents()
        }
        verify { articlesRepository.getAllPublishedArticles() }
    }

    @Test
    fun freshFeedUsesFreshQuery() = runTest {
        every { feedsRepository.getFeedById(Feed.FEED_ID_FRESH) } returns
            flowOf(Feed(id = Feed.FEED_ID_FRESH))
        every { articlesRepository.getAllUnreadArticlesUpdatedAfterTime(any()) } returns
            flowOf(sampleArticles(1))

        val vm = newListViewModel(Feed.FEED_ID_FRESH)
        vm.setSortByMostRecentFirst(true)

        vm.articles.test {
            awaitItem()
            assertThat(awaitItem()).hasSize(1)
            cancelAndIgnoreRemainingEvents()
        }
        verify { articlesRepository.getAllUnreadArticlesUpdatedAfterTime(any()) }
    }

    @Test
    fun numericFeedUsesUnreadForFeedQuery() = runTest {
        val feedId = 42L
        every { feedsRepository.getFeedById(feedId) } returns flowOf(Feed(id = feedId))
        every { articlesRepository.getAllUnreadArticlesForFeed(feedId) } returns flowOf(sampleArticles(9))

        val vm = newListViewModel(feedId)
        vm.setSortByMostRecentFirst(true)

        vm.articles.test {
            awaitItem()
            assertThat(awaitItem()).hasSize(1)
            cancelAndIgnoreRemainingEvents()
        }
        verify { articlesRepository.getAllUnreadArticlesForFeed(feedId) }
    }

    @Test
    fun sortByMostRecentFirstFalsePicksOldestFirstVariant() = runTest {
        val feedId = 42L
        every { feedsRepository.getFeedById(feedId) } returns flowOf(Feed(id = feedId))
        every { articlesRepository.getAllUnreadArticlesForFeedOldestFirst(feedId) } returns
            flowOf(sampleArticles(9))

        val vm = newListViewModel(feedId)
        vm.setSortByMostRecentFirst(false)

        vm.articles.test {
            awaitItem()
            awaitItem()
            cancelAndIgnoreRemainingEvents()
        }
        verify { articlesRepository.getAllUnreadArticlesForFeedOldestFirst(feedId) }
    }

    // --- isMultiFeed / isAllArticlesFeed ---

    @Test
    fun isMultiFeedIsTrueForVirtualFeeds() {
        every { feedsRepository.getFeedById(any()) } returns flowOf(null)
        val vm = newListViewModel(Feed.FEED_ID_ALL_ARTICLES)
        assertThat(vm.isMultiFeed.value).isTrue()
        assertThat(vm.isAllArticlesFeed.value).isTrue()
    }

    @Test
    fun isMultiFeedIsFalseForConcreteFeed() {
        every { feedsRepository.getFeedById(any()) } returns flowOf(null)
        val vm = newListViewModel(feedId = 42L)
        assertThat(vm.isMultiFeed.value).isFalse()
        assertThat(vm.isAllArticlesFeed.value).isFalse()
    }

    // --- refresh behavior ---

    @Test
    fun refreshOnVirtualFeedTriggersAccountRefresh() = runTest {
        every { feedsRepository.getFeedById(any()) } returns flowOf(null)
        coEvery { backgroundJobManager.refresh(fakeAccount) } just Runs

        val vm = newListViewModel(Feed.FEED_ID_ALL_ARTICLES)
        vm.refresh()
        advanceUntilIdle()

        coVerify { backgroundJobManager.refresh(fakeAccount) }
    }

    @Test
    fun refreshOnConcreteFeedTriggersFeedRefresh() = runTest {
        every { feedsRepository.getFeedById(any()) } returns flowOf(null)
        val info = RefreshFeedInfo(workName = "w", collectArticlesWorkerId = UUID.randomUUID())
        coEvery { backgroundJobManager.refreshFeed(fakeAccount, 42L) } returns info
        every {
            backgroundJobManager.isRefreshingByWorkerId(info.collectArticlesWorkerId)
        } returns MutableLiveData(false)

        val vm = newListViewModel(feedId = 42L)
        vm.refresh()
        advanceUntilIdle()

        coVerify { backgroundJobManager.refreshFeed(fakeAccount, 42L) }
    }

    // --- undo / commit state ---

    @Test
    fun setArticleUnreadIncrementsPendingCountAndUndoDecrementsIt() = runTest {
        every { feedsRepository.getFeedById(any()) } returns flowOf(null)
        val action = mockk<SetUnreadAction>(relaxed = true)
        every {
            setFieldActionFactory.createSetUnreadAction(any(), 7L, false)
        } returns action

        val vm = newListViewModel(Feed.FEED_ID_ALL_ARTICLES)
        assertThat(vm.pendingArticlesSetUnread.value).isEqualTo(0)

        vm.setArticleUnread(7L, false)
        assertThat(vm.pendingArticlesSetUnread.value).isEqualTo(1)
        verify { action.execute() }

        vm.undoSetUnreadActions()
        assertThat(vm.pendingArticlesSetUnread.value).isEqualTo(0)
    }

    @Test
    fun commitSetUnreadActionsResetsPendingCount() = runTest {
        every { feedsRepository.getFeedById(any()) } returns flowOf(null)
        val action = mockk<SetUnreadAction>(relaxed = true)
        every {
            setFieldActionFactory.createSetUnreadAction(any(), any(), any())
        } returns action

        val vm = newListViewModel(Feed.FEED_ID_ALL_ARTICLES)
        vm.setArticleUnread(1L, false)
        vm.setArticleUnread(2L, false)
        assertThat(vm.pendingArticlesSetUnread.value).isEqualTo(2)

        vm.commitSetUnreadActions()
        assertThat(vm.pendingArticlesSetUnread.value).isEqualTo(0)
    }

    @Test
    fun setArticleStarredExecutesStarredAction() {
        every { feedsRepository.getFeedById(any()) } returns flowOf(null)
        val action = mockk<SetStarredAction>(relaxed = true)
        every {
            setFieldActionFactory.createSetStarredAction(any(), 5L, true)
        } returns action

        val vm = newListViewModel(Feed.FEED_ID_ALL_ARTICLES)
        vm.setArticleStarred(5L, true)

        verify { action.execute() }
    }

    // --- ArticlesListByTagViewModel ---

    @Test
    fun byTagViewModelUsesUnreadTagQueryWhenNeedUnreadAndMostRecent() = runTest {
        every { articlesRepository.getAllUnreadArticlesForTag("kotlin") } returns
            flowOf(sampleArticles(1))

        val vm = newByTagViewModel("kotlin")
        vm.setSortByMostRecentFirst(true)
        vm.setNeedUnread(true)

        vm.articles.test {
            awaitItem()
            assertThat(awaitItem()).hasSize(1)
            cancelAndIgnoreRemainingEvents()
        }
        verify { articlesRepository.getAllUnreadArticlesForTag("kotlin") }
    }

    @Test
    fun byTagViewModelUsesAllArticlesForTagWhenNeedUnreadFalse() = runTest {
        every { articlesRepository.getAllArticlesForTag("kotlin") } returns
            flowOf(sampleArticles(1, 2))

        val vm = newByTagViewModel("kotlin")
        vm.setSortByMostRecentFirst(true)
        vm.setNeedUnread(false)

        vm.articles.test {
            awaitItem()
            assertThat(awaitItem()).hasSize(2)
            cancelAndIgnoreRemainingEvents()
        }
        verify { articlesRepository.getAllArticlesForTag("kotlin") }
    }

    @Test
    fun byTagViewModelIsMultiFeedIsAlwaysTrue() {
        val vm = newByTagViewModel("kotlin")
        assertThat(vm.isMultiFeed.value).isTrue()
        assertThat(vm.isAllArticlesFeed.value).isFalse()
    }

    @Test
    fun byTagViewModelRefreshTriggersAccountRefresh() = runTest {
        every { backgroundJobManager.refresh(fakeAccount) } just Runs
        val vm = newByTagViewModel("kotlin")

        vm.refresh()

        verify { backgroundJobManager.refresh(fakeAccount) }
    }
}
