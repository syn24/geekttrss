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
        // For Fresh Articles (-3), we want all unread items regardless of age.
        // For All Articles (-4) and regular feeds, we limit to 1 day to handle the "catch up" scenario without downloading history.
        val cutoffTime = if (isFreshFeed) 0L else System.currentTimeMillis() / 1000 - TimeUnit.DAYS.toSeconds(1)
        val limitDescription = if (isFreshFeed) "no time limit" else "limit 1 day"

        Timber.i("Collecting new articles for feed $feedId ($limitDescription)")

        // Use sinceId=0 to get all articles, then filter by time locally.
        // This ensures we don't miss articles if article IDs are not monotonically increasing.
        val sinceId = 0L
        Timber.d("Feed $feedId: Using sinceId=$sinceId (fetching all recent articles)")

        var offset = 0
        var totalFetched = 0
        val maxArticlesToFetch = 1000

        // Fetch newest articles first (gradually=false)
        var articlesRaw = getArticles(feedId, sinceId, offset,
            includeAttachments = true, gradually = false)
        Timber.d("Feed $feedId: First batch returned ${articlesRaw.size} articles from API (sinceId=$sinceId, offset=$offset)")

        while (articlesRaw.isNotEmpty() && totalFetched < maxArticlesToFetch) {
            // Filter out articles older than cutoff (only applies to non-Fresh feeds)
            val recentArticles = articlesRaw.filter { it.article.lastTimeUpdate >= cutoffTime }
            Timber.d("Feed $feedId: After cutoff filter: ${recentArticles.size} articles (cutoffTime=$cutoffTime)")

            if (recentArticles.isNotEmpty()) {
                databaseService.runInTransaction {
                    insertArticles(recentArticles)
                }
                totalFetched += recentArticles.size
                Timber.d("Feed $feedId: Inserted ${recentArticles.size} articles, total fetched: $totalFetched")
            }

            // Check if we reached the end of the "fresh" window
            // If the last article in the batch is older than cutoff, we can stop (unless it's Fresh feed where we want all)
            val oldestInBatch = articlesRaw.minByOrNull { it.article.lastTimeUpdate }?.article?.lastTimeUpdate ?: 0

            // For regular feeds, stop if we hit old articles.
            // For fresh feed, we technically could stop if we hit something older than we care about,
            // but user said "fetch all unread", so we continue until maxArticlesToFetch is hit or API is empty.
            if (!isFreshFeed && oldestInBatch < cutoffTime) {
                Timber.i("Reached articles older than cutoff. Stopping sync for feed $feedId.")
                break
            }

            offset += articlesRaw.size

            if (totalFetched < maxArticlesToFetch) {
                articlesRaw = getArticles(feedId, sinceId, offset, includeAttachments = true, gradually = false)
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
