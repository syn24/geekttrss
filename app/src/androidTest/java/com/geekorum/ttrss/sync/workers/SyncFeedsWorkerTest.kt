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

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.workDataOf
import com.geekorum.ttrss.core.ActualCoroutineDispatchersModule
import com.geekorum.ttrss.core.CoroutineDispatchersProvider
import com.geekorum.ttrss.data.ArticlesDatabase
import com.geekorum.ttrss.data.Category
import com.geekorum.ttrss.data.DiskDatabaseModule
import com.geekorum.ttrss.data.Feed
import com.geekorum.ttrss.network.ApiService
import com.geekorum.ttrss.network.TinyrssApiModule
import com.geekorum.ttrss.sync.DatabaseService
import com.geekorum.ttrss.webapi.ApiCallException
import com.google.common.truth.Truth.assertThat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@UninstallModules(
    ActualCoroutineDispatchersModule::class,
    WorkersModule::class,
    TinyrssApiModule::class,
    DiskDatabaseModule::class,
)
class SyncFeedsWorkerTest {

    private lateinit var workerBuilder: TestListenableWorkerBuilder<SyncFeedsWorker>
    private lateinit var apiService: FakeApiService

    private val testDispatcher = StandardTestDispatcher()

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    @Inject
    lateinit var databaseService: DatabaseService

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @JvmField
    @BindValue
    val dispatchers = CoroutineDispatchersProvider(
        main = testDispatcher,
        io = testDispatcher,
        computation = testDispatcher,
    )

    @Module(includes = [FakeSyncWorkersModule::class])
    @InstallIn(SingletonComponent::class)
    inner class MockModule {
        @Provides
        fun providesApiService(): ApiService = apiService

        @Provides
        @Singleton
        fun providesAppDatabase(application: Application): ArticlesDatabase {
            return buildInMemoryDatabase(application, dispatchers.io.asExecutor())
        }
    }

    @BeforeTest
    fun setUp() {
        hiltRule.inject()
        Dispatchers.setMain(testDispatcher)
        apiService = FakeApiService()

        val applicationContext: Context = ApplicationProvider.getApplicationContext()
        workerBuilder = TestListenableWorkerBuilder(applicationContext)
        workerBuilder.setWorkerFactory(hiltWorkerFactory)
        workerBuilder.setInputData(workDataOf(
            BaseSyncWorker.PARAM_ACCOUNT_NAME to "fake",
            BaseSyncWorker.PARAM_ACCOUNT_TYPE to "fake_type",
        ))
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun successfulSyncInsertsCategoriesAndFeeds() = runTest {
        apiService.categoriesResponse = listOf(
            Category(id = 1, title = "News")
        )
        apiService.feedsResponse = listOf(
            Feed(id = 10, title = "Feed A", catId = 1),
            Feed(id = 11, title = "Feed B", catId = 1),
        )

        val worker = workerBuilder.build()
        val result = worker.doWork()

        assertThat(result).isEqualTo(ListenableWorker.Result.success())
        assertThat(databaseService.getFeeds().map { it.id }).containsExactly(10L, 11L)
        assertThat(databaseService.getCategories().map { it.id }).contains(1L)
    }

    @Test
    fun oldFeedsAreDeletedWhenNotInServerResponse() = runTest {
        // seed an old feed that the server no longer returns
        databaseService.insertCategories(listOf(Category(id = 1, title = "News")))
        databaseService.insertFeeds(listOf(Feed(id = 99, title = "obsolete", catId = 1)))

        apiService.categoriesResponse = listOf(Category(id = 1, title = "News"))
        apiService.feedsResponse = listOf(Feed(id = 10, title = "current", catId = 1))

        val worker = workerBuilder.build()
        val result = worker.doWork()

        assertThat(result).isEqualTo(ListenableWorker.Result.success())
        val remainingIds = databaseService.getFeeds().map { it.id }
        assertThat(remainingIds).contains(10L)
        assertThat(remainingIds).doesNotContain(99L)
    }

    @Test
    fun virtualCategoriesWithNegativeIdsAreSkipped() = runTest {
        // Virtual categories (id < 0) should not be inserted
        apiService.categoriesResponse = listOf(
            Category(id = -1, title = "Uncategorized"),
            Category(id = 1, title = "Real"),
        )
        apiService.feedsResponse = emptyList()

        val worker = workerBuilder.build()
        worker.doWork()

        val ids = databaseService.getCategories().map { it.id }
        assertThat(ids).contains(1L)
        assertThat(ids).doesNotContain(-1L)
    }

    @Test
    fun apiFailureReturnsResultFailure() = runTest {
        apiService.shouldThrow = true

        val worker = workerBuilder.build()
        val result = worker.doWork()

        assertThat(result).isEqualTo(ListenableWorker.Result.failure())
    }

    private class FakeApiService : MockApiService() {
        var categoriesResponse: List<Category> = emptyList()
        var feedsResponse: List<Feed> = emptyList()
        var shouldThrow: Boolean = false

        override suspend fun getCategories(): List<Category> {
            if (shouldThrow) throw ApiCallException(ApiCallException.ApiError.API_UNKNOWN, "boom")
            return categoriesResponse
        }

        override suspend fun getFeeds(): List<Feed> {
            if (shouldThrow) throw ApiCallException(ApiCallException.ApiError.API_UNKNOWN, "boom")
            return feedsResponse
        }
    }
}
