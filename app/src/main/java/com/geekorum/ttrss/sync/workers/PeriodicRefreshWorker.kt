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

import android.accounts.AccountManager
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.geekorum.ttrss.accounts.AccountAuthenticator
import com.geekorum.ttrss.providers.ArticlesContract
import com.geekorum.ttrss.sync.SyncContract
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

/**
 * Periodically triggers a manual sync for every TTRSS account.
 *
 * Scheduled via WorkManager (JobScheduler under the hood) so that it remains reliable
 * on Samsung devices where SyncAdapter periodic syncs are killed by battery optimization.
 *
 * Uses [ContentResolver.SYNC_EXTRAS_MANUAL] to bypass auto-sync throttling — this reuses
 * the entire existing [com.geekorum.ttrss.sync.ArticleSynchronizer] pipeline, which
 * already refreshes the launcher badge in its finally block.
 */
@HiltWorker
class PeriodicRefreshWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val accountManager: AccountManager
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val accounts = accountManager.getAccountsByType(AccountAuthenticator.TTRSS_ACCOUNT_TYPE)
        if (accounts.isEmpty()) {
            Timber.i("PeriodicRefreshWorker: no TTRSS accounts, skipping")
            return Result.success()
        }
        val extras = Bundle().apply {
            putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true)
            putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true)
            putBoolean(SyncContract.EXTRA_UPDATE_FEED_ICONS, false)
        }
        for (account in accounts) {
            Timber.i("PeriodicRefreshWorker: requesting sync for ${account.name}")
            ContentResolver.requestSync(account, ArticlesContract.AUTHORITY, extras)
        }
        return Result.success()
    }
}
