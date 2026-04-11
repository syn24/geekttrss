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

import app.cash.turbine.test
import com.geekorum.ttrss.data.Article
import com.geekorum.ttrss.data.ArticleDao
import com.geekorum.ttrss.data.ArticleWithFeed
import com.google.common.truth.Truth.assertThat
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [ArticlesRepository] — a pure facade over [ArticleDao].
 * Asserts each public method delegates to the correct DAO call.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ArticlesRepositoryTest {

    private lateinit var articleDao: ArticleDao
    private lateinit var repository: ArticlesRepository

    private val sampleList: List<ArticleWithFeed> = emptyList()
    private val sampleFlow = flowOf(sampleList)

    @Before
    fun setup() {
        articleDao = mockk(relaxed = true)
        repository = ArticlesRepository(articleDao)
    }

    @Test
    fun getAllArticlesDelegatesToDao() {
        every { articleDao.getAllArticles() } returns sampleFlow
        every { articleDao.getAllArticlesOldestFirst() } returns sampleFlow

        assertThat(repository.getAllArticles()).isSameInstanceAs(sampleFlow)
        assertThat(repository.getAllArticlesOldestFirst()).isSameInstanceAs(sampleFlow)

        verify { articleDao.getAllArticles() }
        verify { articleDao.getAllArticlesOldestFirst() }
    }

    @Test
    fun getAllUnreadArticlesDelegatesToDao() {
        every { articleDao.getAllUnreadArticles() } returns sampleFlow
        every { articleDao.getAllUnreadArticlesOldestFirst() } returns sampleFlow

        repository.getAllUnreadArticles()
        repository.getAllUnreadArticlesOldestFirst()

        verify { articleDao.getAllUnreadArticles() }
        verify { articleDao.getAllUnreadArticlesOldestFirst() }
    }

    @Test
    fun getAllStarredArticlesDelegatesToDao() {
        every { articleDao.getAllStarredArticles() } returns sampleFlow
        every { articleDao.getAllStarredArticlesOldestFirst() } returns sampleFlow

        repository.getAllStarredArticles()
        repository.getAllStarredArticlesOldestFirst()

        verify { articleDao.getAllStarredArticles() }
        verify { articleDao.getAllStarredArticlesOldestFirst() }
    }

    @Test
    fun getAllPublishedArticlesDelegatesToDao() {
        every { articleDao.getAllPublishedArticles() } returns sampleFlow
        every { articleDao.getAllPublishedArticlesOldestFirst() } returns sampleFlow

        repository.getAllPublishedArticles()
        repository.getAllPublishedArticlesOldestFirst()

        verify { articleDao.getAllPublishedArticles() }
        verify { articleDao.getAllPublishedArticlesOldestFirst() }
    }

    @Test
    fun getUnreadArticlesForFeedDelegatesToDao() {
        every { articleDao.getAllUnreadArticlesForFeed(42L) } returns sampleFlow
        every { articleDao.getAllUnreadArticlesForFeedOldestFirst(42L) } returns sampleFlow

        repository.getAllUnreadArticlesForFeed(42L)
        repository.getAllUnreadArticlesForFeedOldestFirst(42L)

        verify { articleDao.getAllUnreadArticlesForFeed(42L) }
        verify { articleDao.getAllUnreadArticlesForFeedOldestFirst(42L) }
    }

    @Test
    fun getArticlesForTagDelegatesToDao() {
        every { articleDao.getAllArticlesForTag("kotlin") } returns sampleFlow
        every { articleDao.getAllArticlesForTagOldestFirst("kotlin") } returns sampleFlow
        every { articleDao.getAllUnreadArticlesForTag("kotlin") } returns sampleFlow
        every { articleDao.getAllUnreadArticlesForTagOldestFirst("kotlin") } returns sampleFlow

        repository.getAllArticlesForTag("kotlin")
        repository.getAllArticlesForTagOldestFirst("kotlin")
        repository.getAllUnreadArticlesForTag("kotlin")
        repository.getAllUnreadArticlesForTagOldestFirst("kotlin")

        verify { articleDao.getAllArticlesForTag("kotlin") }
        verify { articleDao.getAllArticlesForTagOldestFirst("kotlin") }
        verify { articleDao.getAllUnreadArticlesForTag("kotlin") }
        verify { articleDao.getAllUnreadArticlesForTagOldestFirst("kotlin") }
    }

    @Test
    fun getArticleByIdIsDelegatedToDaoWithDistinctUntilChanged() = runTest {
        val article = Article(id = 7, feedId = 1)
        every { articleDao.getArticleById(7) } returns flowOf(article, article)

        repository.getArticleById(7).test {
            assertThat(awaitItem()).isEqualTo(article)
            // distinctUntilChanged dedupes identical emissions
            awaitComplete()
        }
        verify { articleDao.getArticleById(7) }
    }

    @Test
    fun setArticleUnreadDelegatesToDao() = runTest {
        coEvery { articleDao.updateArticleUnread(11, false) } returns 1

        repository.setArticleUnread(11, false)

        coVerify { articleDao.updateArticleUnread(11, false) }
    }

    @Test
    fun setArticleStarredDelegatesToDao() = runTest {
        coEvery { articleDao.updateArticleMarked(11, true) } returns 1

        repository.setArticleStarred(11, true)

        coVerify { articleDao.updateArticleMarked(11, true) }
    }

    @Test
    fun setAllArticlesUnreadDelegatesToDao() = runTest {
        coEvery { articleDao.updateAllArticleUnread(false) } returns 5

        repository.setAllArticlesUnread(false)

        coVerify { articleDao.updateAllArticleUnread(false) }
    }

    @Test
    fun setArticlesUnreadForFeedDelegatesToDao() = runTest {
        coEvery { articleDao.updateArticleUnreadForFeed(3, false) } returns 2

        repository.setArticlesUnreadForFeed(3, false)

        coVerify { articleDao.updateArticleUnreadForFeed(3, false) }
    }

    @Test
    fun setStarredAndPublishedArticlesUnreadDelegateToDao() = runTest {
        coEvery { articleDao.updateStarredArticlesUnread(false) } returns 1
        coEvery { articleDao.updatePublishedArticlesUnread(false) } returns 1

        repository.setStarredArticlesUnread(false)
        repository.setPublishedArticlesUnread(false)

        coVerify { articleDao.updateStarredArticlesUnread(false) }
        coVerify { articleDao.updatePublishedArticlesUnread(false) }
    }

    @Test
    fun getUnreadArticlesRandomizedDelegatesToDao() = runTest {
        coEvery { articleDao.getUnreadArticlesRandomized(5) } returns emptyList()

        val result = repository.getUnreadArticlesRandomized(5)

        assertThat(result).isEmpty()
        coVerify { articleDao.getUnreadArticlesRandomized(5) }
    }

    @Test
    fun getUnreadArticlesForTagDelegatesToDaoWithDefaultCount() = runTest {
        coEvery { articleDao.getUnreadArticlesForTag("kotlin", 3) } returns emptyList()

        repository.getUnreadArticlesForTag("kotlin")

        coVerify { articleDao.getUnreadArticlesForTag("kotlin", 3) }
    }

    @Test
    fun getMostUnreadTagsDelegatesToDao() = runTest {
        coEvery { articleDao.getMostUnreadTags(10) } returns listOf("kotlin", "android")

        val result = repository.getMostUnreadTags(10)

        assertThat(result).containsExactly("kotlin", "android").inOrder()
    }

    @Test
    fun searchArticlesDelegatesToDao() {
        val pagingSource = mockk<androidx.paging.PagingSource<Int, ArticleWithFeed>>()
        every { articleDao.searchArticles("hello") } returns pagingSource

        val result = repository.searchArticles("hello")

        assertThat(result).isSameInstanceAs(pagingSource)
    }

    @Test
    fun countFlowsDelegateToDao() {
        every { articleDao.getAllArticlesCount() } returns flowOf(10)
        every { articleDao.getAllUnreadArticlesCount() } returns flowOf(3)
        every { articleDao.getAllStarredArticlesCount() } returns flowOf(1)
        every { articleDao.getUnreadArticlesForFeedCount(42L) } returns flowOf(2)

        repository.getAllArticlesCount()
        repository.getAllUnreadArticlesCount()
        repository.getAllStarredArticlesCount()
        repository.getUnreadArticlesCountForFeed(42L)

        verify { articleDao.getAllArticlesCount() }
        verify { articleDao.getAllUnreadArticlesCount() }
        verify { articleDao.getAllStarredArticlesCount() }
        verify { articleDao.getUnreadArticlesForFeedCount(42L) }
    }

    @Test
    fun freshTimeSecEmitsValueRoughly36HoursAgo() = runTest {
        val before = System.currentTimeMillis() / 1000
        repository.freshTimeSec.test {
            val firstEmission = awaitItem()
            val after = System.currentTimeMillis() / 1000
            // Must be within [now-36h-1, now-36h+1]
            assertThat(firstEmission).isAtLeast(before - 3600 * 36 - 1)
            assertThat(firstEmission).isAtMost(after - 3600 * 36 + 1)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun getFreshUnreadCountObservesFreshTimeSecAndDelegatesToDao() = runTest {
        every { articleDao.getUnreadArticlesUpdatedAfterTimeCount(any()) } returns flowOf(7)

        repository.getFreshUnreadCount().test {
            assertThat(awaitItem()).isEqualTo(7)
            cancelAndIgnoreRemainingEvents()
        }

        verify { articleDao.getUnreadArticlesUpdatedAfterTimeCount(any()) }
    }
}
