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
import android.accounts.AccountManager
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.geekorum.ttrss.accounts.AccountAuthenticator
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

/**
 * Instrumented test for [PeriodicRefreshWorker].
 *
 * Verifies the worker's branching on account presence. We cannot easily assert that
 * [android.content.ContentResolver.requestSync] was invoked from an instrumented test
 * (no Robolectric shadows available), so we focus on the observable `Result`.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class PeriodicRefreshWorkerTest {

    private lateinit var workerBuilder: TestListenableWorkerBuilder<PeriodicRefreshWorker>
    private lateinit var androidAccountManager: AccountManager

    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val fakeAccount = Account("fred", AccountAuthenticator.TTRSS_ACCOUNT_TYPE)

    @BeforeTest
    fun setUp() {
        hiltRule.inject()
        val applicationContext: Context = ApplicationProvider.getApplicationContext()
        androidAccountManager = AccountManager.get(applicationContext)
        workerBuilder = TestListenableWorkerBuilder(applicationContext)
        workerBuilder.setWorkerFactory(hiltWorkerFactory)
    }

    @AfterTest
    fun tearDown() {
        androidAccountManager.removeAccountExplicitly(fakeAccount)
    }

    @Test
    fun returnsSuccessWhenNoAccountsAreRegistered() = runTest {
        // Skip when the emulator has a real TTRSS account installed — we can't
        // remove accounts belonging to other UIDs from an instrumented test.
        val existing = androidAccountManager.getAccountsByType(AccountAuthenticator.TTRSS_ACCOUNT_TYPE)
        assumeTrue("Emulator has a real TTRSS account; skipping", existing.isEmpty())

        val worker = workerBuilder.build()
        val result = worker.doWork()

        assertThat(result).isEqualTo(ListenableWorker.Result.success())
    }

    @Test
    fun returnsSuccessWhenAtLeastOneAccountIsRegistered() = runTest {
        // add a TTRSS account so that the worker hits the requestSync branch
        androidAccountManager.addAccountExplicitly(fakeAccount, "password", null)
        assertThat(
            androidAccountManager.getAccountsByType(AccountAuthenticator.TTRSS_ACCOUNT_TYPE)
        ).asList().contains(fakeAccount)

        val worker = workerBuilder.build()
        val result = worker.doWork()

        assertThat(result).isEqualTo(ListenableWorker.Result.success())
    }
}
