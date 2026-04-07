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
package com.geekorum.ttrss.add_feed

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import com.geekorum.ttrss.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 * Launch [AddFeedActivity] activity
 */
@AndroidEntryPoint
class AddFeedLauncherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val freshContext = createPackageContext(packageName, 0)
            val intent = intent.apply {
                component = ComponentName.createRelative(freshContext, "com.geekorum.ttrss.manage_feeds.add_feed.AddFeedActivity")
            }
            startActivity(intent)
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.wtf(e, "Unable to create our package context")
        }
        finish()
    }
}
