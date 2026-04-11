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
package com.geekorum.ttrss.data

import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekorum.ttrss.data.ArticlesDatabase.Tables
import com.geekorum.ttrss.providers.ArticlesContract
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * Integration tests for [ArticleDao] using an in-memory Room database.
 *
 * Validates the "Feed Display Behavior" rules described in CLAUDE.md:
 * Fresh Articles = unread AND within 36h; Starred/Published = regardless of read state;
 * Normal feeds = unread only.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {

    private lateinit var database: ArticlesDatabase
    private lateinit var dao: ArticleDao

    // time constants used to populate articles at known offsets
    private val now: Long = 1_700_000_000L
    private val oneHourAgo = now - 3_600L
    private val twoDaysAgo = now - 2 * 24 * 3_600L

    @BeforeTest
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArticlesDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.articleDao()
        seedDatabase(database.openHelper.writableDatabase)
    }

    @AfterTest
    fun tearDown() {
        database.close()
    }

    @Test
    fun getArticleByIdReturnsTheRequestedArticle() = runTest {
        val article = dao.getArticleById(1).first()
        assertThat(article).isNotNull()
        assertThat(article!!.id).isEqualTo(1)
        assertThat(article.title).isEqualTo("unread recent")
    }

    @Test
    fun getArticleByIdReturnsNullForUnknownId() = runTest {
        assertThat(dao.getArticleById(999).first()).isNull()
    }

    @Test
    fun getAllArticlesReturnsEverythingInDatabase() = runTest {
        val articles = dao.getAllArticles().first()
        // Seed has 6 articles total (ids 1..6)
        assertThat(articles).hasSize(6)
    }

    @Test
    fun getAllUnreadArticlesFiltersOutReadOnes() = runTest {
        val unread = dao.getAllUnreadArticles().first()
        // Seed: ids 1, 2, 4, 5 are unread; 3 and 6 are read
        assertThat(unread.map { it.article.id }).containsExactly(1L, 2L, 4L, 5L)
    }

    @Test
    fun getAllUnreadArticlesUpdatedAfterTimeOnlyReturnsFreshUnreadArticles() = runTest {
        val cutoff = now - 36 * 3_600L
        val fresh = dao.getAllUnreadArticlesUpdatedAfterTime(cutoff).first()
        // Only articles that are (unread AND lastTimeUpdate >= cutoff)
        // Seed: id=1 (unread, recent), id=4 (unread, recent, feed=2)
        // id=2 is unread but old, id=5 is unread but old
        assertThat(fresh.map { it.article.id }).containsExactly(1L, 4L)
    }

    @Test
    fun getUnreadArticlesUpdatedAfterTimeCountMatchesFreshQuery() = runTest {
        val cutoff = now - 36 * 3_600L
        val count = dao.getUnreadArticlesUpdatedAfterTimeCount(cutoff).first()
        assertThat(count).isEqualTo(2)
    }

    @Test
    fun getAllStarredArticlesReturnsStarredRegardlessOfReadState() = runTest {
        val starred = dao.getAllStarredArticles().first()
        // Seed: id=3 starred+read, id=4 starred+unread
        assertThat(starred.map { it.article.id }).containsExactly(4L, 3L)
    }

    @Test
    fun getAllPublishedArticlesReturnsPublishedRegardlessOfReadState() = runTest {
        val published = dao.getAllPublishedArticles().first()
        // Seed: id=5 published+unread, id=6 published+read
        assertThat(published.map { it.article.id }).containsExactly(5L, 6L)
    }

    @Test
    fun getAllUnreadArticlesForFeedExcludesRead() = runTest {
        val feed1Unread = dao.getAllUnreadArticlesForFeed(feedId = 1).first()
        // Seed: feed=1 has id=1,2,3 — but 3 is read
        assertThat(feed1Unread.map { it.article.id }).containsExactly(1L, 2L)
    }

    @Test
    fun getAllUnreadArticlesForFeedOldestFirstOrdersAscending() = runTest {
        val oldestFirst = dao.getAllUnreadArticlesForFeedOldestFirst(feedId = 1).first()
        // id=2 is older than id=1
        assertThat(oldestFirst.map { it.article.id }).containsExactly(2L, 1L).inOrder()
    }

    @Test
    fun countFlowsReturnCorrectValues() = runTest {
        assertThat(dao.getAllArticlesCount().first()).isEqualTo(6)
        assertThat(dao.getAllUnreadArticlesCount().first()).isEqualTo(4)
        assertThat(dao.getAllStarredArticlesCount().first()).isEqualTo(2)
        assertThat(dao.getUnreadArticlesForFeedCount(1).first()).isEqualTo(2)
    }

    @Test
    fun getUnreadArticlesCountOnceReturnsSnapshot() = runTest {
        assertThat(dao.getUnreadArticlesCountOnce()).isEqualTo(4)
    }

    @Test
    fun updateArticleUnreadChangesRowAndFlowReemits() = runTest {
        val changed = dao.updateArticleUnread(articleId = 1, isUnread = false)
        assertThat(changed).isEqualTo(1)

        val unread = dao.getAllUnreadArticles().first()
        assertThat(unread.map { it.article.id }).containsExactly(2L, 4L, 5L)
    }

    @Test
    fun updateArticleUnreadReturnsZeroForUnknownId() = runTest {
        val changed = dao.updateArticleUnread(articleId = 999, isUnread = false)
        assertThat(changed).isEqualTo(0)
    }

    @Test
    fun updateAllArticleUnreadAffectsEveryArticle() = runTest {
        val changed = dao.updateAllArticleUnread(false)
        assertThat(changed).isEqualTo(6)
        assertThat(dao.getAllUnreadArticlesCount().first()).isEqualTo(0)
    }

    @Test
    fun updateArticleUnreadForFeedOnlyAffectsThatFeed() = runTest {
        val changed = dao.updateArticleUnreadForFeed(feedId = 1, isUnread = false)
        assertThat(changed).isEqualTo(3) // all 3 articles on feed 1
        // feed 2 articles remain unread
        assertThat(dao.getAllUnreadArticlesForFeed(feedId = 2).first()).isNotEmpty()
        assertThat(dao.getAllUnreadArticlesForFeed(feedId = 1).first()).isEmpty()
    }

    @Test
    fun updateArticleMarkedTogglesStarred() = runTest {
        val changed = dao.updateArticleMarked(articleId = 1, isMarked = true)
        assertThat(changed).isEqualTo(1)
        val starredIds = dao.getAllStarredArticles().first().map { it.article.id }
        assertThat(starredIds).contains(1L)
    }

    @Test
    fun getUnreadArticlesRandomizedReturnsRequestedCount() = runTest {
        val result = dao.getUnreadArticlesRandomized(count = 2)
        assertThat(result).hasSize(2)
        // All returned items must be unread
        assertThat(result.all { it.article.isUnread }).isTrue()
    }

    @Test
    fun getUnreadArticlesRandomizedCapsAtAvailable() = runTest {
        val result = dao.getUnreadArticlesRandomized(count = 100)
        assertThat(result).hasSize(4) // only 4 unread in seed
    }

    // --- Seed ---
    // Creates: 2 feeds, 6 articles with known states:
    //   id=1: feed=1, unread, recent       → appears in unread, feed1 unread, fresh
    //   id=2: feed=1, unread, old          → appears in unread, feed1 unread (not fresh)
    //   id=3: feed=1, read,   recent, starred → appears in starred, all
    //   id=4: feed=2, unread, recent, starred → appears in unread, feed2 unread, starred, fresh
    //   id=5: feed=2, unread, old, published  → appears in unread, published (not fresh)
    //   id=6: feed=2, read,   old, published  → appears in published, all
    private fun seedDatabase(db: SupportSQLiteDatabase) {
        db.insert(
            Tables.CATEGORIES, SQLiteDatabase.CONFLICT_NONE,
            contentValuesOf(
                ArticlesContract.Category._ID to 0,
                ArticlesContract.Category.TITLE to "cat",
                ArticlesContract.Category.UNREAD_COUNT to 0
            )
        )
        insertFeed(db, id = 1, title = "feed one")
        insertFeed(db, id = 2, title = "feed two")

        insertArticle(db, id = 1, feedId = 1, title = "unread recent", unread = 1, updated = now,       starred = 0, published = 0)
        insertArticle(db, id = 2, feedId = 1, title = "unread old",    unread = 1, updated = twoDaysAgo, starred = 0, published = 0)
        insertArticle(db, id = 3, feedId = 1, title = "read starred",  unread = 0, updated = oneHourAgo, starred = 1, published = 0)
        insertArticle(db, id = 4, feedId = 2, title = "fresh starred", unread = 1, updated = now,       starred = 1, published = 0)
        insertArticle(db, id = 5, feedId = 2, title = "unread pub",    unread = 1, updated = twoDaysAgo, starred = 0, published = 1)
        insertArticle(db, id = 6, feedId = 2, title = "read pub",      unread = 0, updated = twoDaysAgo, starred = 0, published = 1)
    }

    private fun insertFeed(db: SupportSQLiteDatabase, id: Long, title: String) {
        db.insert(
            Tables.FEEDS, SQLiteDatabase.CONFLICT_NONE,
            contentValuesOf(
                ArticlesContract.Feed._ID to id,
                ArticlesContract.Feed.TITLE to title,
                ArticlesContract.Feed.URL to "http://example.com/$id",
                ArticlesContract.Feed.CAT_ID to 0,
                ArticlesContract.Feed.UNREAD_COUNT to 0,
                ArticlesContract.Feed.LAST_TIME_UPDATE to 0,
                ArticlesContract.Feed.DISPLAY_TITLE to title,
                ArticlesContract.Feed.IS_SUBSCRIBED to 1,
            )
        )
    }

    private fun insertArticle(
        db: SupportSQLiteDatabase,
        id: Long,
        feedId: Long,
        title: String,
        unread: Int,
        updated: Long,
        starred: Int,
        published: Int,
    ) {
        db.insert(
            Tables.ARTICLES, SQLiteDatabase.CONFLICT_NONE,
            contentValuesOf(
                ArticlesContract.Article._ID to id,
                ArticlesContract.Article.TITLE to title,
                ArticlesContract.Article.CONTENT to "",
                ArticlesContract.Article.SCORE to 0,
                ArticlesContract.Article.PUBLISHED to published,
                ArticlesContract.Article.LAST_TIME_UPDATE to updated,
                ArticlesContract.Article.UNREAD to unread,
                ArticlesContract.Article.TRANSIENT_UNREAD to unread,
                ArticlesContract.Article.STARRED to starred,
                ArticlesContract.Article.IS_UPDATED to 0,
                ArticlesContract.Article.FEED_ID to feedId,
                ArticlesContract.Article.LINK to "",
                ArticlesContract.Article.TAGS to "",
                ArticlesContract.Article.AUTHOR to "",
                ArticlesContract.Article.FLAVOR_IMAGE_URI to "",
                ArticlesContract.Article.CONTENT_EXCERPT to "",
            )
        )
    }
}
