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
package com.geekorum.ttrss.background_job

import android.accounts.Account
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.testing.WorkManagerTestInitHelper
import com.geekorum.ttrss.accounts.AccountAuthenticator
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Instrumented tests for [BackgroundJobManager]'s WorkManager-driven paths.
 *
 * `refresh(account)` delegates to `ContentResolver.requestSync` and cannot be observed
 * from here without Robolectric shadows, so it is not covered. All other enqueue-side
 * paths are covered via the WorkManager test harness.
 */
class BackgroundJobManagerTest {

    private val fakeAccount = Account("fred", AccountAuthenticator.TTRSS_ACCOUNT_TYPE)

    private lateinit var application: Application
    private lateinit var workManager: WorkManager
    private lateinit var manager: BackgroundJobManager

    @Before
    fun setUp() {
        application = ApplicationProvider.getApplicationContext()
        WorkManagerTestInitHelper.initializeTestWorkManager(application)
        workManager = WorkManager.getInstance(application)
        manager = BackgroundJobManager(application)
    }

    @Test
    fun setupPeriodicJobsEnqueuesPeriodicPurgeWithExpectedName() {
        manager.setupPeriodicJobs()

        val infos = workManager
            .getWorkInfosForUniqueWork(BackgroundJobManager.PERIODIC_PURGE_JOB)
            .get()
        assertThat(infos).isNotEmpty()
        assertThat(infos.first().state).isAnyOf(WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING)
    }

    @Test
    fun setupPeriodicJobsEnqueuesPeriodicRefreshWithExpectedName() {
        manager.setupPeriodicJobs()

        val infos = workManager
            .getWorkInfosForUniqueWork(BackgroundJobManager.PERIODIC_REFRESH_WORK)
            .get()
        assertThat(infos).isNotEmpty()
        assertThat(infos.first().state).isAnyOf(WorkInfo.State.ENQUEUED, WorkInfo.State.RUNNING)
    }

    @Test
    fun setupPeriodicJobsAlsoTriggersImmediatePurge() {
        manager.setupPeriodicJobs()

        val infos = workManager
            .getWorkInfosForUniqueWork("immediate_purge_startup")
            .get()
        assertThat(infos).isNotEmpty()
    }

    @Test
    fun triggerImmediatePurgeEnqueuesStartupWork() {
        manager.triggerImmediatePurge()

        val infos = workManager
            .getWorkInfosForUniqueWork("immediate_purge_startup")
            .get()
        assertThat(infos).hasSize(1)
    }

    @Test
    fun setupPeriodicJobsIsIdempotentForThePeriodicPurge() {
        manager.setupPeriodicJobs()
        val firstId = workManager
            .getWorkInfosForUniqueWork(BackgroundJobManager.PERIODIC_PURGE_JOB)
            .get()
            .first()
            .id

        // Calling again with ExistingPeriodicWorkPolicy.KEEP should NOT replace the work.
        manager.setupPeriodicJobs()
        val secondId = workManager
            .getWorkInfosForUniqueWork(BackgroundJobManager.PERIODIC_PURGE_JOB)
            .get()
            .first()
            .id

        assertThat(secondId).isEqualTo(firstId)
    }

    @Test
    fun setupPeriodicJobsIsIdempotentForThePeriodicRefresh() {
        manager.setupPeriodicJobs()
        val firstId = workManager
            .getWorkInfosForUniqueWork(BackgroundJobManager.PERIODIC_REFRESH_WORK)
            .get()
            .first()
            .id

        manager.setupPeriodicJobs()
        val secondId = workManager
            .getWorkInfosForUniqueWork(BackgroundJobManager.PERIODIC_REFRESH_WORK)
            .get()
            .first()
            .id

        assertThat(secondId).isEqualTo(firstId)
    }

    @Test
    fun refreshFeedEnqueuesUniqueWorkWithExpectedName() = runTest {
        val info = manager.refreshFeed(fakeAccount, feedId = 42)

        assertThat(info.workName).isEqualTo("refresh-feed-42")

        val infos = workManager
            .getWorkInfosForUniqueWork("refresh-feed-42")
            .get()
        // The chain is SendTransactions -> (SyncFeeds + CollectNewArticles) -> UpdateArticleStatus = 4 work requests.
        assertThat(infos).hasSize(4)
        // collectArticlesWorkerId is one of the four enqueued IDs.
        assertThat(infos.map { it.id }).contains(info.collectArticlesWorkerId)
    }

    @Test
    fun cancelRefreshCancelsAllTaggedWork() = runTest {
        manager.refreshFeed(fakeAccount, feedId = 7)
        val beforeIds = workManager
            .getWorkInfosForUniqueWork("refresh-feed-7")
            .get()
            .map { it.id }
        assertThat(beforeIds).isNotEmpty()

        manager.cancelRefresh(fakeAccount)

        val after = workManager
            .getWorkInfosForUniqueWork("refresh-feed-7")
            .get()
        // Every work request in the chain must end up in the CANCELLED state.
        assertThat(after.map { it.state }).containsExactly(
            WorkInfo.State.CANCELLED,
            WorkInfo.State.CANCELLED,
            WorkInfo.State.CANCELLED,
            WorkInfo.State.CANCELLED,
        )
    }
}
