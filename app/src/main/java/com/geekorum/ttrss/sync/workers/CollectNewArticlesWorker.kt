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
package com.geekorum.ttrss.sync.workers

import android.accounts.Account
import android.content.Context
import android.content.OperationApplicationException
import android.os.RemoteException
import android.security.NetworkSecurityPolicy
import androidx.hilt.work.HiltWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.geekorum.ttrss.core.CoroutineDispatchersProvider
import com.geekorum.ttrss.data.Article
import com.geekorum.ttrss.data.ArticleWithAttachments
import com.geekorum.ttrss.data.ArticlesTags
import com.geekorum.ttrss.htmlparsers.ImageUrlExtractor
import com.geekorum.ttrss.sync.BackgroundDataUsageManager
import com.geekorum.ttrss.sync.DatabaseService
import com.geekorum.ttrss.sync.HttpCacher
import com.geekorum.ttrss.webapi.ApiCallException
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.*
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Collect all the new articles from a feed
 */
@HiltWorker
class CollectNewArticlesWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    syncWorkerComponentBuilder: SyncWorkerComponent.Builder,
    private val dispatchers: CoroutineDispatchersProvider,
    private val backgroundDataUsageManager: BackgroundDataUsageManager,
    private val imageUrlExtractor: ImageUrlExtractor,
    private val httpCacher: HttpCacher
) : FeedArticlesWorker(context, workerParams, syncWorkerComponentBuilder) {

    private val databaseService: DatabaseService = syncWorkerComponent.databaseService

    companion object {
        const val PARAM_FEED_ID = "feed_id"

        fun getInputData(account: Account, feedId: Long): Data {
            return workDataOf(
                    SyncWorkerFactory.PARAM_ACCOUNT_NAME to account.name,
                    SyncWorkerFactory.PARAM_ACCOUNT_TYPE to account.type,
                    PARAM_FEED_ID to feedId
            )
        }
    }

    private var feedId: Long = Long.MIN_VALUE

    override suspend fun doWork(): Result = withContext(dispatchers.io) {
        feedId = inputData.getLong(PARAM_FEED_ID, Long.MIN_VALUE)
        if (feedId == Long.MIN_VALUE) {
            Timber.w("No feed_id was specified. Skip work")
            return@withContext Result.success()
        }
        collectNewArticles()
        Result.success()
    }

    @Throws(ApiCallException::class, RemoteException::class, OperationApplicationException::class)
    private suspend fun collectNewArticles() = coroutineScope {
        val isFreshFeed = feedId == -3L
        // Fresh feed: no time limit (server already filters to unread articles <36h).
        // Other feeds: only keep read articles from the last day to bound DB growth.
        // Unread articles are always kept regardless of age so nav counts match the detail view.
        val cutoffTime = if (isFreshFeed) 0L else System.currentTimeMillis() / 1000 - TimeUnit.DAYS.toSeconds(1)

        // Phase 1: fetch articles newer than what we already have (sinceId bound).
        val sinceId = getLatestArticleId()
        Timber.i("Collecting new articles for feed $feedId (sinceId=$sinceId)")

        var offset = 0
        var totalFetched = 0
        val maxArticlesToFetch = 1000

        var articlesRaw = getArticles(feedId, sinceId, offset, includeAttachments = true, gradually = false)

        while (articlesRaw.isNotEmpty() && totalFetched < maxArticlesToFetch) {
            // Keep unread articles regardless of age; discard old read articles to bound DB size.
            val articlesToInsert = articlesRaw.filter { it.article.isUnread || it.article.lastTimeUpdate >= cutoffTime }
            if (articlesToInsert.isNotEmpty()) {
                databaseService.runInTransaction {
                    insertArticles(articlesToInsert)
                }
                totalFetched += articlesToInsert.size
                Timber.d("Feed $feedId: Inserted ${articlesToInsert.size} articles, total: $totalFetched")
            }
            offset += articlesRaw.size
            articlesRaw = getArticles(feedId, sinceId, offset, includeAttachments = true, gradually = false)
        }

        // Phase 2: fetch all currently unread articles (sinceId=0, view_mode=unread).
        // This catches old unread articles whose IDs are lower than sinceId and would
        // therefore never be returned by Phase 1 — the exact cause of nav-count vs
        // detail-view discrepancies on feeds like "Ibook".
        if (!isFreshFeed) {
            Timber.i("Fetching unread articles for feed $feedId to sync old unread items")
            var unreadOffset = 0
            var unreadArticlesRaw = getUnreadArticles(feedId, unreadOffset, includeAttachments = true)
            while (unreadArticlesRaw.isNotEmpty()) {
                databaseService.runInTransaction {
                    insertArticles(unreadArticlesRaw)
                }
                Timber.d("Feed $feedId: Inserted ${unreadArticlesRaw.size} unread articles")
                unreadOffset += unreadArticlesRaw.size
                unreadArticlesRaw = getUnreadArticles(feedId, unreadOffset, includeAttachments = true)
            }
        }
    }

    private suspend fun getLatestArticleId(): Long {
        return databaseService.getLatestArticleIdFromFeed(feedId) ?: 0
    }

    private suspend fun insertArticles(articles: List<ArticleWithAttachments>) {
        val articlesOnly = articles.map { it.article }
        databaseService.insertArticles(articlesOnly)
        val articlesTags = articlesOnly.flatMap {
            val tags = it.tags.split(",")
                .map(String::trim)
                .filter(String::isNotEmpty)
            tags.map {tag ->
                ArticlesTags(it.id, tag)
            }
        }
        databaseService.insertArticleTags(articlesTags)
        val attachments = articles.flatMap { it.attachments }
        databaseService.insertAttachments(attachments)
    }

    private fun CoroutineScope.cacheArticlesImages(articles: List<Article>) {
        articles.filter {
            it.isUnread
        }.forEach { cacheArticleImages(it) }
    }

    private fun CoroutineScope.cacheArticleImages(article: Article) {
        if (!backgroundDataUsageManager.canDownloadArticleImages()) {
            return
        }
        imageUrlExtractor.extract(article.content, baseUri = article.link)
                .mapNotNull { it.toHttpUrlOrNull() }
                .filter { it.canBeCache() }
                .forEach {
                    launch(dispatchers.io) {
                        try {
                            httpCacher.cacheHttpRequest(it)
                        } catch (e: IOException) {
                            Timber.w(e, "Unable to cache request $it")
                        }
                    }
                }
    }

    private fun HttpUrl.canBeCache(): Boolean {
        if (scheme == "http" && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(host)) {
            Timber.d("Can't cache $this, clear text traffic not permitted")
            return false
        }
        return true
    }

}
