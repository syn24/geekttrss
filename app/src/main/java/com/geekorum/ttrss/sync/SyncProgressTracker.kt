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
package com.geekorum.ttrss.sync

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Tracks sync progress so the UI can stop the refresh spinner once
 * article collection is complete, even if background work (status updates,
 * purge) is still running.
 */
@Singleton
class SyncProgressTracker @Inject constructor() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _syncActive = MutableStateFlow(false)
    private val _articlesCollected = MutableStateFlow(false)

    /** True while a sync is running AND articles have not yet been collected. */
    val isCollectingArticles: StateFlow<Boolean> = combine(_syncActive, _articlesCollected) { active, collected ->
        active && !collected
    }.stateIn(scope, SharingStarted.Eagerly, false)

    fun onSyncStarted() {
        _syncActive.value = true
        _articlesCollected.value = false
    }

    fun onArticlesCollected() {
        _articlesCollected.value = true
    }

    fun onSyncFinished() {
        _syncActive.value = false
    }
}
