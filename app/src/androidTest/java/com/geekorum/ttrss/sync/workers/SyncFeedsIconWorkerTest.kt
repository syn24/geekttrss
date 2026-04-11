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
import com.geekorum.ttrss.network.ServerInfo
import com.geekorum.ttrss.network.TinyrssApiModule
import com.geekorum.ttrss.sync.DatabaseService
import com.geekorum.ttrss.webapi.ApiCallException
import com.google.common.truth.Truth.assertThat
import okio.BufferedSource
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
class SyncFeedsIconWorkerTest {

    private lateinit var workerBuilder: TestListenableWorkerBuilder<SyncFeedsIconWorker>
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
    fun emptyDatabaseReturnsSuccess() = runTest {
        // No feeds in the DB → synchronizer has nothing to process.
        val worker = workerBuilder.build()
        val result = worker.doWork()

        assertThat(result).isEqualTo(ListenableWorker.Result.success())
    }

    @Test
    fun withFeedsStillReturnsSuccessEvenWhenIconLookupFails() = runTest {
        // Seed a feed — icon lookup will fail (no real network), but the worker should
        // still return success because per-feed failures are caught inside the synchronizer.
        databaseService.insertCategories(listOf(Category(id = 1, title = "cat")))
        databaseService.insertFeeds(
            listOf(Feed(id = 1, title = "feed", url = "https://example.invalid/rss", catId = 1))
        )

        val worker = workerBuilder.build()
        val result = worker.doWork()

        assertThat(result).isEqualTo(ListenableWorker.Result.success())
    }

    private class FakeApiService : MockApiService() {
        override suspend fun getServerInfo(): ServerInfo =
            ServerInfo(apiUrl = "https://test.exemple.com/", apiLevel = 20, feedsIconsUrl = null, serverVersion = "1.0")

        override suspend fun getFeedIcon(feedId: Long): BufferedSource {
            // Throw a checked Exception so FeedIconSynchronizer.dispatchUpdateFeedIcons's
            // catch(Exception) branch swallows it. Using TODO() would leak a
            // NotImplementedError (an Error, not an Exception) and fail the test.
            throw ApiCallException(ApiCallException.ApiError.API_UNKNOWN, "no icon in test")
        }
    }
}
