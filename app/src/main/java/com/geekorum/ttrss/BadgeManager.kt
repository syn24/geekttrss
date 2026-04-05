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
package com.geekorum.ttrss

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.geekorum.ttrss.data.ArticleDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Keeps the launcher icon badge count in sync with the number of unread articles.
 * The badge is driven by a silent, ongoing notification with [NotificationCompat.Builder.setNumber].
 * Supported on Samsung One UI, MIUI, and other launchers that read badge counts from notifications.
 *
 * Call [start] once from [Application.onCreate].
 */
@Singleton
class BadgeManager @Inject constructor(
    private val application: Application,
    private val articleDao: ArticleDao
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val notificationManager = NotificationManagerCompat.from(application)

    companion object {
        private const val CHANNEL_ID = "unread_articles_badge"
        const val NOTIF_ID = 2
    }

    fun start() {
        createChannel()
        scope.launch {
            articleDao.getAllUnreadArticlesCount().collectLatest { count ->
                updateBadge(count)
            }
        }
    }

    /**
     * One-shot badge update — queries the current unread count and posts the notification.
     * Call this at the end of a background sync to ensure the badge is current even if the
     * process is about to be killed before the Flow collector runs.
     */
    suspend fun updateBadgeNow() {
        val count = articleDao.getUnreadArticlesCountOnce()
        updateBadge(count)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                application.getString(R.string.notif_channel_badge_name),
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                setSound(null, null)
                enableVibration(false)
                setShowBadge(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun updateBadge(count: Int) {
        if (count <= 0) {
            notificationManager.cancel(NOTIF_ID)
            return
        }
        val notification = NotificationCompat.Builder(application, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(application.resources.getQuantityString(
                R.plurals.notif_unread_articles_title, count, count))
            .setNumber(count)
            .setSilent(true)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .build()
        @Suppress("MissingPermission")
        notificationManager.notify(NOTIF_ID, notification)
    }
}
