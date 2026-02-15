/*
 * Geekttrss is a RSS feed reader application on the Android Platform.
 *
 * Copyright (C) 2017-2026 by Frederic-Charles Barthelery.
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
package com.geekorum.ttrss.network

import com.geekorum.ttrss.webapi.TinyRssApi
import com.geekorum.ttrss.webapi.model.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assume
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.io.File
import java.io.FileInputStream
import java.util.Properties
import java.util.concurrent.TimeUnit
import kotlin.test.assertNotNull

/**
 * Integration test that connects to a real TT-RSS instance for debugging and performance analysis.
 *
 * This test requires a `test-credentials.properties` file in the test resources directory with:
 * - ttrss.url=<your TT-RSS server URL>
 * - ttrss.user=<username>
 * - ttrss.password=<password>
 *
 * The test will be skipped if the credentials file is not found or credentials are invalid.
 *
 * Usage:
 * - Run individual tests to debug specific API calls
 * - Check console output for timing information
 * - Use breakpoints to inspect response data
 */
class RealServerIntegrationTest {

    private lateinit var tinyRssApi: TinyRssApi
    private lateinit var serverUrl: String
    private lateinit var username: String
    private lateinit var password: String
    private var sessionId: String? = null

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    @Before
    fun setUp() {
        val credentials = loadCredentials()
        Assume.assumeNotNull("Credentials file not found or invalid - skipping integration tests", credentials)

        serverUrl = credentials!!.getProperty("ttrss.url")
        username = credentials.getProperty("ttrss.user")
        password = credentials.getProperty("ttrss.password")

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        var apiUrl = serverUrl
        if (!apiUrl.endsWith("/")) {
            apiUrl += "/"
        }

        val jsonConverterFactory = json.asConverterFactory("application/json".toMediaType())
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(jsonConverterFactory)
            .client(okHttpClient)
            .build()

        tinyRssApi = retrofit.create(TinyRssApi::class.java)
    }

    private fun loadCredentials(): Properties? {
        // Try loading from classpath (test resources)
        val resourceStream = javaClass.classLoader?.getResourceAsStream("test-credentials.properties")
        if (resourceStream != null) {
            return Properties().apply { load(resourceStream) }
        }

        // Try loading from project root directory
        val projectRootFile = File("test-credentials.properties")
        if (projectRootFile.exists()) {
            return Properties().apply { load(FileInputStream(projectRootFile)) }
        }

        // Try loading from app directory
        val appDirFile = File("app/src/test/resources/test-credentials.properties")
        if (appDirFile.exists()) {
            return Properties().apply { load(FileInputStream(appDirFile)) }
        }

        println("WARNING: test-credentials.properties not found. Integration tests will be skipped.")
        return null
    }

    private suspend fun ensureLoggedIn() {
        if (sessionId == null) {
            val loginResponse = tinyRssApi.login(LoginRequestPayload(username, password))
            sessionId = loginResponse.sessionId
            assertNotNull(sessionId, "Failed to login - session ID is null")
            println("Logged in successfully. Session ID: $sessionId")
        }
    }

    @Test
    fun testLogin() = runBlocking {
        val startTime = System.currentTimeMillis()
        val response = tinyRssApi.login(LoginRequestPayload(username, password))
        val duration = System.currentTimeMillis() - startTime

        println("=== LOGIN TEST ===")
        println("Duration: ${duration}ms")
        println("Session ID: ${response.sessionId}")
        println("API Level: ${response.apiLevel}")
        println("Status: ${response.status}")

        assertNotNull(response.sessionId, "Session ID should not be null")
        sessionId = response.sessionId
    }

    @Test
    fun testGetServerInfo() = runBlocking {
        ensureLoggedIn()

        println("\n=== SERVER INFO TEST ===")

        // Get Version
        val versionPayload = GetVersionRequestPayload().apply { this.sessionId = this@RealServerIntegrationTest.sessionId }
        val startVersion = System.currentTimeMillis()
        val versionResponse = tinyRssApi.getVersion(versionPayload)
        println("Version: ${versionResponse.version} (${System.currentTimeMillis() - startVersion}ms)")

        // Get API Level
        val apiLevelPayload = GetApiLevelRequestPayload().apply { this.sessionId = this@RealServerIntegrationTest.sessionId }
        val startApiLevel = System.currentTimeMillis()
        val apiLevelResponse = tinyRssApi.getApiLevel(apiLevelPayload)
        println("API Level: ${apiLevelResponse.level} (${System.currentTimeMillis() - startApiLevel}ms)")

        // Get Config
        val configPayload = GetConfigRequestPayload().apply { this.sessionId = this@RealServerIntegrationTest.sessionId }
        val startConfig = System.currentTimeMillis()
        val configResponse = tinyRssApi.getConfig(configPayload)
        println("Icons URL: ${configResponse.iconsUrl} (${System.currentTimeMillis() - startConfig}ms)")
    }

    @Test
    fun testGetFeeds() = runBlocking {
        ensureLoggedIn()

        println("\n=== GET FEEDS TEST ===")

        val payload = GetFeedsRequestPayload(
            includeNested = true,
            unreadOnly = false,
            categorieId = GetFeedsRequestPayload.CATEGORY_ID_ALL_EXCLUDE_VIRTUALS
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startTime = System.currentTimeMillis()
        val response = tinyRssApi.getFeeds(payload)
        val duration = System.currentTimeMillis() - startTime

        val feeds = response.result
        println("Duration: ${duration}ms")
        println("Total feeds: ${feeds.size}")
        println("\nFeeds breakdown:")
        feeds.forEachIndexed { index, feed ->
            println("  ${index + 1}. [ID: ${feed.id}] ${feed.title} - Unread: ${feed.nbUnreadArticles}")
        }
    }

    @Test
    fun testGetCategories() = runBlocking {
        ensureLoggedIn()

        println("\n=== GET CATEGORIES TEST ===")

        val payload = GetCategoriesRequestPayload(
            includeNested = true,
            unreadOnly = false
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startTime = System.currentTimeMillis()
        val response = tinyRssApi.getCategories(payload)
        val duration = System.currentTimeMillis() - startTime

        val categories = response.result
        println("Duration: ${duration}ms")
        println("Total categories: ${categories.size}")
        println("\nCategories:")
        categories.forEach { category ->
            println("  [ID: ${category.id}] ${category.title} - Unread: ${category.nbUnreadArticles}")
        }
    }

    @Test
    fun testGetAllArticles() = runBlocking {
        ensureLoggedIn()

        println("\n=== GET ALL ARTICLES TEST ===")

        val payload = GetArticlesRequestPayload(
            feedId = -4, // All articles virtual feed
            viewMode = GetArticlesRequestPayload.ViewMode.ALL_ARTICLES,
            showContent = false, // Don't load content for performance test
            showExcerpt = true,
            includeAttachments = false,
            limit = 200
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startTime = System.currentTimeMillis()
        val response = tinyRssApi.getArticles(payload)
        val duration = System.currentTimeMillis() - startTime

        val articles = response.result
        println("Duration: ${duration}ms")
        println("Total articles fetched: ${articles.size}")

        val unreadCount = articles.count { it.unread }
        val readCount = articles.size - unreadCount
        println("Unread: $unreadCount, Read: $readCount")

        if (articles.isNotEmpty()) {
            println("\nFirst 10 articles:")
            articles.take(10).forEachIndexed { index, article ->
                val status = if (article.unread) "UNREAD" else "read"
                println("  ${index + 1}. [$status] ${article.title.take(60)}...")
            }

            // Analyze article ages
            val now = System.currentTimeMillis() / 1000
            val articleAges = articles.mapNotNull {
                if (it.lastUpdatedTimestamp > 0) (now - it.lastUpdatedTimestamp) / 86400 else null
            }
            if (articleAges.isNotEmpty()) {
                println("\nArticle age distribution (days):")
                println("  Oldest: ${articleAges.maxOrNull()} days")
                println("  Newest: ${articleAges.minOrNull()} days")
                println("  Average: ${articleAges.average().toInt()} days")
            }
        }
    }

    @Test
    fun testGetFreshArticles() = runBlocking {
        ensureLoggedIn()

        println("\n=== GET FRESH (UNREAD) ARTICLES TEST ===")

        val payload = GetArticlesRequestPayload(
            feedId = -4, // All articles virtual feed
            viewMode = GetArticlesRequestPayload.ViewMode.UNREAD,
            showContent = false,
            showExcerpt = true,
            includeAttachments = false,
            limit = 200
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startTime = System.currentTimeMillis()
        val response = tinyRssApi.getArticles(payload)
        val duration = System.currentTimeMillis() - startTime

        val articles = response.result
        println("Duration: ${duration}ms")
        println("Total unread articles: ${articles.size}")

        val allUnread = articles.all { it.unread }
        println("All articles are unread: $allUnread")

        if (articles.isNotEmpty()) {
            println("\nFirst 10 unread articles:")
            articles.take(10).forEachIndexed { index, article ->
                println("  ${index + 1}. ${article.title.take(60)}...")
            }
        }
    }

    @Test
    fun testGetArticlesFromSpecificFeed() = runBlocking {
        ensureLoggedIn()

        println("\n=== GET ARTICLES FROM SPECIFIC FEED TEST ===")

        // First get feeds to find a valid feed ID
        val feedsPayload = GetFeedsRequestPayload(
            includeNested = true,
            unreadOnly = false,
            categorieId = GetFeedsRequestPayload.CATEGORY_ID_ALL_EXCLUDE_VIRTUALS
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val feedsResponse = tinyRssApi.getFeeds(feedsPayload)
        val feeds = feedsResponse.result

        if (feeds.isEmpty()) {
            println("No feeds found - skipping test")
            return@runBlocking
        }

        // Test with first feed
        val testFeed = feeds.first()
        println("Testing with feed: ${testFeed.title} (ID: ${testFeed.id})")

        val articlesPayload = GetArticlesRequestPayload(
            feedId = testFeed.id,
            viewMode = GetArticlesRequestPayload.ViewMode.ALL_ARTICLES,
            showContent = false,
            showExcerpt = true,
            includeAttachments = false,
            limit = 50
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startTime = System.currentTimeMillis()
        val articlesResponse = tinyRssApi.getArticles(articlesPayload)
        val duration = System.currentTimeMillis() - startTime

        val articles = articlesResponse.result
        println("Duration: ${duration}ms")
        println("Articles from '${testFeed.title}': ${articles.size}")

        if (articles.isNotEmpty()) {
            println("\nFirst 5 articles:")
            articles.take(5).forEachIndexed { index, article ->
                println("  ${index + 1}. ${article.title.take(60)}...")
            }
        }
    }

    @Test
    fun testGetArticlesWithFullContent() = runBlocking {
        ensureLoggedIn()

        println("\n=== GET ARTICLES WITH FULL CONTENT TEST ===")

        val payload = GetArticlesRequestPayload(
            feedId = -4,
            viewMode = GetArticlesRequestPayload.ViewMode.UNREAD,
            showContent = true, // Include full content
            showExcerpt = true,
            includeAttachments = true, // Include attachments
            limit = 10
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startTime = System.currentTimeMillis()
        val response = tinyRssApi.getArticles(payload)
        val duration = System.currentTimeMillis() - startTime

        val articles = response.result
        println("Duration: ${duration}ms")
        println("Articles with content: ${articles.size}")

        articles.forEachIndexed { index, article ->
            println("\n--- Article ${index + 1} ---")
            println("Title: ${article.title}")
            println("Feed ID: ${article.feedId}")
            println("Content length: ${article.content.length} chars")
            println("Excerpt: ${article.excerpt.take(100)}...")
            println("Attachments: ${article.attachments.size}")
            article.attachments.forEach { attachment ->
                println("  - ${attachment.contentType}: ${attachment.contentUrl}")
            }
        }
    }

    @Test
    fun testPerformanceBenchmark() = runBlocking {
        ensureLoggedIn()

        println("\n=== PERFORMANCE BENCHMARK ===")

        val benchmarks = mutableListOf<Pair<String, Long>>()

        // Benchmark: Get feeds
        repeat(3) { iteration ->
            val payload = GetFeedsRequestPayload(
                includeNested = true,
                unreadOnly = false,
                categorieId = GetFeedsRequestPayload.CATEGORY_ID_ALL_EXCLUDE_VIRTUALS
            ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

            val start = System.currentTimeMillis()
            tinyRssApi.getFeeds(payload)
            benchmarks.add("GetFeeds #${iteration + 1}" to System.currentTimeMillis() - start)
        }

        // Benchmark: Get unread articles (different limits)
        listOf(50, 100, 200).forEach { limit ->
            val payload = GetArticlesRequestPayload(
                feedId = -4,
                viewMode = GetArticlesRequestPayload.ViewMode.UNREAD,
                showContent = false,
                showExcerpt = true,
                limit = limit
            ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

            val start = System.currentTimeMillis()
            val response = tinyRssApi.getArticles(payload)
            val duration = System.currentTimeMillis() - start
            benchmarks.add("GetArticles (limit=$limit, got=${response.result.size})" to duration)
        }

        // Benchmark: Get articles with content vs without
        val payloadWithContent = GetArticlesRequestPayload(
            feedId = -4,
            viewMode = GetArticlesRequestPayload.ViewMode.UNREAD,
            showContent = true,
            showExcerpt = true,
            limit = 50
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startWithContent = System.currentTimeMillis()
        tinyRssApi.getArticles(payloadWithContent)
        benchmarks.add("GetArticles with content (50)" to System.currentTimeMillis() - startWithContent)

        val payloadWithoutContent = GetArticlesRequestPayload(
            feedId = -4,
            viewMode = GetArticlesRequestPayload.ViewMode.UNREAD,
            showContent = false,
            showExcerpt = false,
            limit = 50
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val startWithoutContent = System.currentTimeMillis()
        tinyRssApi.getArticles(payloadWithoutContent)
        benchmarks.add("GetArticles without content (50)" to System.currentTimeMillis() - startWithoutContent)

        println("\nBenchmark Results:")
        println("-".repeat(60))
        benchmarks.forEach { (name, duration) ->
            println("${name.padEnd(45)} ${duration}ms")
        }
        println("-".repeat(60))

        val avgFeedsTime = benchmarks.filter { it.first.startsWith("GetFeeds") }.map { it.second }.average()
        println("Average GetFeeds time: ${avgFeedsTime.toInt()}ms")
    }

    @Test
    fun testAnalyzeArticleDistribution() = runBlocking {
        ensureLoggedIn()

        println("\n=== ARTICLE DISTRIBUTION ANALYSIS ===")

        // Get feeds first
        val feedsPayload = GetFeedsRequestPayload(
            includeNested = true,
            unreadOnly = false,
            categorieId = GetFeedsRequestPayload.CATEGORY_ID_ALL_EXCLUDE_VIRTUALS
        ).apply { this.sessionId = this@RealServerIntegrationTest.sessionId }

        val feeds = tinyRssApi.getFeeds(feedsPayload).result

        println("Feed statistics:")
        println("-".repeat(80))
        println("${"Feed Name".padEnd(50)} ${"Unread".padStart(10)} ${"Total".padStart(10)}")
        println("-".repeat(80))

        var totalUnread = 0
        feeds.sortedByDescending { it.nbUnreadArticles }.forEach { feed ->
            totalUnread += feed.nbUnreadArticles
            println("${feed.title.take(48).padEnd(50)} ${feed.nbUnreadArticles.toString().padStart(10)}")
        }

        println("-".repeat(80))
        println("Total unread across all feeds: $totalUnread")
    }
}





