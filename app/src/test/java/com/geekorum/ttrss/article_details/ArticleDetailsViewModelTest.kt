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
package com.geekorum.ttrss.article_details

import android.accounts.Account
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.geekorum.ttrss.articles_list.ArticlesRepository
import com.geekorum.ttrss.articles_list.SetArticleFieldAction
import com.geekorum.ttrss.articles_list.SetStarredAction
import com.geekorum.ttrss.articles_list.SetUnreadAction
import com.geekorum.ttrss.data.Article
import com.geekorum.ttrss.data.ArticleContentIndexed
import com.geekorum.ttrss.data.ArticleWithFeed
import com.geekorum.ttrss.data.Feed
import com.geekorum.ttrss.data.FeedWithFavIcon
import com.geekorum.ttrss.network.TtRssBrowserLauncher
import com.geekorum.ttrss.session.SessionActivityComponent
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Unit tests for [ArticleDetailsViewModel].
 *
 * Uses Robolectric (via AndroidJUnit4) so that `parseAsHtml()` on titles doesn't crash.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ArticleDetailsViewModelTest {

    @get:Rule
    val archRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var articlesRepository: ArticlesRepository
    private lateinit var setFieldActionFactory: SetArticleFieldAction.Factory
    private lateinit var component: SessionActivityComponent
    private lateinit var componentFactory: SessionActivityComponent.Factory
    private lateinit var browserLauncher: TtRssBrowserLauncher

    private val fakeAccount = Account("fred", "com.test")

    private val baseArticle = Article(
        id = 42,
        feedId = 1,
        contentData = ArticleContentIndexed(title = "Hello &amp; world", tags = "kotlin, android"),
        isUnread = true,
        link = "https://example.com/article"
    )

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

        browserLauncher = mockk(relaxed = true) {
            every { browserIcon } returns MutableStateFlow(null)
        }

        // default: no article found
        every { articlesRepository.getArticleById(any()) } returns flowOf(null)
        coEvery { articlesRepository.getUnreadArticlesForTag(any(), any()) } returns emptyList()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun newViewModel(articleId: Long = 42): ArticleDetailsViewModel =
        ArticleDetailsViewModel(articleId, browserLauncher, componentFactory)

    @Test
    fun initWarmsUpBrowserLauncher() {
        newViewModel()
        verify { browserLauncher.warmUp() }
    }

    @Test
    fun onClearedShutsDownBrowserLauncher() {
        // Use a reflection-free approach: onCleared is protected in parent but open in VM
        // We can call it by exposing a ViewModelStore wrapper. Simpler: call the public method.
        val vm = newViewModel()
        // Access protected onCleared via a subclass trick is not needed —
        // ViewModel.onCleared() is protected; invoke via reflection to simulate clearing.
        val method = androidx.lifecycle.ViewModel::class.java.getDeclaredMethod("onCleared")
        method.isAccessible = true
        method.invoke(vm)
        verify { browserLauncher.shutdown() }
    }

    @Test
    fun articleFlowEmitsArticleWithHtmlEntitiesDecoded() = runTest {
        every { articlesRepository.getArticleById(42) } returns flowOf(baseArticle)

        val vm = newViewModel()
        vm.article.test {
            // initial null, then real
            assertThat(awaitItem()).isNull()
            val emitted = awaitItem()
            assertThat(emitted).isNotNull()
            // "Hello &amp; world" → "Hello & world" after parseAsHtml
            assertThat(emitted!!.title).isEqualTo("Hello & world")
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun articleFlowEmitsNullWhenArticleNotFound() = runTest {
        every { articlesRepository.getArticleById(42) } returns flowOf(null)

        val vm = newViewModel()
        vm.article.test {
            assertThat(awaitItem()).isNull()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun additionalArticlesReturnsUpToThreeRelatedArticles() = runTest {
        every { articlesRepository.getArticleById(42) } returns flowOf(baseArticle)

        val related1 = ArticleWithFeed(
            article = Article(id = 100, feedId = 1, contentData = ArticleContentIndexed(title = "rel1")),
            feed = FeedWithFavIcon(feed = Feed(id = 1), favIcon = null)
        )
        val related2 = ArticleWithFeed(
            article = Article(id = 101, feedId = 1, contentData = ArticleContentIndexed(title = "rel2")),
            feed = FeedWithFavIcon(feed = Feed(id = 1), favIcon = null)
        )
        val related3 = ArticleWithFeed(
            article = Article(id = 102, feedId = 1, contentData = ArticleContentIndexed(title = "rel3")),
            feed = FeedWithFavIcon(feed = Feed(id = 1), favIcon = null)
        )
        coEvery { articlesRepository.getUnreadArticlesForTag("kotlin") } returns listOf(related1, related2)
        coEvery { articlesRepository.getUnreadArticlesForTag("android") } returns listOf(related3)

        val vm = newViewModel()
        vm.additionalArticles.test {
            // initial empty
            assertThat(awaitItem()).isEmpty()
            val result = awaitItem()
            assertThat(result).hasSize(3)
            assertThat(result.map { it.article.id }.toSet()).containsExactly(100L, 101L, 102L)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun additionalArticlesExcludesTheCurrentArticleFromRelatedResults() = runTest {
        every { articlesRepository.getArticleById(42) } returns flowOf(baseArticle)

        // Related list includes the current article — should be filtered out
        val self = ArticleWithFeed(
            article = baseArticle,
            feed = FeedWithFavIcon(feed = Feed(id = 1), favIcon = null)
        )
        val other = ArticleWithFeed(
            article = Article(id = 100, feedId = 1, contentData = ArticleContentIndexed(title = "other")),
            feed = FeedWithFavIcon(feed = Feed(id = 1), favIcon = null)
        )
        coEvery { articlesRepository.getUnreadArticlesForTag(any()) } returns listOf(self, other)

        val vm = newViewModel()
        vm.additionalArticles.test {
            awaitItem() // empty
            val result = awaitItem()
            assertThat(result.map { it.article.id }).doesNotContain(42L)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onStarChangedExecutesStarredActionForCurrentArticle() = runTest {
        every { articlesRepository.getArticleById(42) } returns flowOf(baseArticle)
        val action = mockk<SetStarredAction>(relaxed = true)
        every { setFieldActionFactory.createSetStarredAction(any(), 42L, true) } returns action

        val vm = newViewModel()
        // Let the article flow materialize so article.value is populated
        vm.article.test {
            awaitItem()
            awaitItem()

            vm.onStarChanged(true)
            verify { action.execute() }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun onStarChangedDoesNothingWhenArticleIsNull() {
        every { articlesRepository.getArticleById(42) } returns flowOf(null)

        val vm = newViewModel()
        vm.onStarChanged(true)

        verify(exactly = 0) { setFieldActionFactory.createSetStarredAction(any(), any(), any()) }
    }

    @Test
    fun setArticleUnreadExecutesUnreadActionForCurrentArticle() = runTest {
        every { articlesRepository.getArticleById(42) } returns flowOf(baseArticle)
        val action = mockk<SetUnreadAction>(relaxed = true)
        every { setFieldActionFactory.createSetUnreadAction(any(), 42L, false) } returns action

        val vm = newViewModel()
        vm.article.test {
            awaitItem()
            awaitItem()

            vm.setArticleUnread(false)
            verify { action.execute() }
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun toggleArticleReadFlipsCurrentUnreadState() = runTest {
        every { articlesRepository.getArticleById(42) } returns flowOf(baseArticle) // isUnread = true
        val action = mockk<SetUnreadAction>(relaxed = true)
        // toggle on an unread article should call setArticleUnread(false)
        every { setFieldActionFactory.createSetUnreadAction(any(), 42L, false) } returns action

        val vm = newViewModel()
        vm.article.test {
            awaitItem()
            awaitItem()

            vm.toggleArticleRead()

            verify { setFieldActionFactory.createSetUnreadAction(any(), 42L, false) }
            cancelAndIgnoreRemainingEvents()
        }
    }
}
