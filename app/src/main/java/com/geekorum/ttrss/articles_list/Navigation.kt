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
package com.geekorum.ttrss.articles_list

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.geekorum.ttrss.R
import com.geekorum.ttrss.article_details.ArticleDetailActivity
import com.geekorum.ttrss.articles_list.search.ArticlesSearchScreen
import com.geekorum.ttrss.data.Feed
import com.geekorum.ttrss.settings.SettingsActivity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object NavRoutes {

    @Serializable
    data class ArticlesList(
        @SerialName("feed_id")
        val feedId: Long = Feed.FEED_ID_FRESH,
        @SerialName("feed_name")
        val feedName: String? = "Fresh Articles"
    )

    @Serializable
    data class ArticlesListByTag(
        val tag: String
    )

    @Serializable
    data class Search(
        val query: String = ""
    )

    fun getLabelForDestination(context: Context, destination: NavDestination) = when {
        destination.hasRoute<ArticlesList>() -> "{feed_name}"
        destination.hasRoute<ArticlesListByTag>() -> "#{tag}"
        else -> null
    }

    fun isTopLevelDestination(destination: NavDestination) = when {
        destination.hasRoute<Search>() -> true
        destination.hasRoute<ArticlesList>() -> true
        destination.hasRoute<ArticlesListByTag>() -> true
        else -> false
    }
}

class ArticlesListScreenArgs(
    val feedId: Long,
    val feedName: String?
) {
    constructor(arguments: Bundle) : this(
        feedId = arguments.getLong("feed_id"),
        feedName = arguments.getString("feed_name"))
}

@Composable
fun ArticlesListNavHost(
    windowSizeClass: WindowSizeClass,
    activityViewModel: ActivityViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    NavHost(navController = navController, startDestination = NavRoutes.ArticlesList()) {
        composable<NavRoutes.ArticlesList>(
            deepLinks = listOf(
                navDeepLink { uriPattern = "app://feeds/{feed_id}?feed_name={feed_name}" }
            )) {
            ArticlesListScreen(
                feedId = it.toRoute<NavRoutes.ArticlesList>().feedId,
                activityViewModel = activityViewModel,
                windowSizeClass = windowSizeClass,
                contentPadding = contentPadding)
        }

        composable<NavRoutes.ArticlesListByTag> {
            ArticlesListByTagScreen(
                tag = it.toRoute<NavRoutes.ArticlesListByTag>().tag,
                activityViewModel = activityViewModel,
                windowSizeClass = windowSizeClass, contentPadding = contentPadding)
        }

        composable<NavRoutes.Search> {
            ArticlesSearchScreen(
                query = it.toRoute<NavRoutes.Search>().query,
                activityViewModel = activityViewModel, windowSizeClass = windowSizeClass)
        }
    }
}

 fun NavController.navigateToFeed(feedId: Long = Feed.FEED_ID_FRESH, feedTitle: String? = null) {
     // Always create a new destination so feedId changes are applied.
     val route = NavRoutes.ArticlesList(feedId, feedTitle)
     navigate(route) {
         popUpTo(graph.findStartDestination().route!!) {
             saveState = false
         }
         launchSingleTop = false
         restoreState = false
     }
 }

fun NavController.navigateToTag(tag: String) {
    val destination = NavRoutes.ArticlesListByTag(tag)
    navigate(destination) {
        popUpTo<NavRoutes.ArticlesListByTag> {
            inclusive = true
        }
        launchSingleTop = true
    }
}


fun NavController.navigateToSettings() {
    val intent = Intent(context, SettingsActivity::class.java)
    context.startActivity(intent)
}


fun NavController.navigateToArticle(articleId: Long) {
    val intent = Intent(context, ArticleDetailActivity::class.java).apply {
        data = context.getString(R.string.article_details_data_pattern)
            .replace("{article_id}", articleId.toString())
            .toUri()
    }
    context.startActivity(intent)
}

fun NavController.navigateToSearch(query: String = "") {
    val destination = NavRoutes.Search(query)
    navigate(destination, navOptions = navOptions {
        popUpTo<NavRoutes.Search> {
            inclusive = true
        }
    })
}

fun NavController.navigateToManageFeeds() {
    val activityClass = "com.geekorum.ttrss.manage_feeds.ManageFeedsActivity"
    try {
        val intent = Intent().setClassName(context.packageName, activityClass)
        context.startActivity(intent)
    } catch (e: Exception) {
        // Fallback or Log: This happens if the Dynamic Feature Module is not installed correctly
        // often happens when installing single APK instead of Bundle/App-Slices in Debug
        android.util.Log.e("Navigation", "Could not start ManageFeedsActivity: $activityClass", e)
        android.widget.Toast.makeText(context, "Error: Manage Feeds module not installed.", android.widget.Toast.LENGTH_LONG).show()
    }
}

fun createFeedDeepLink(feed: Feed, title: String): Uri {
    return "app://feeds/${feed.id}?feed_name=${title}".toUri()
}

/**
 * Check if we are on destination T before returning the route.
 */
inline fun <reified T: Any> NavBackStackEntry.toRouteOrNull(): T? {
    return if (destination.hasRoute<T>()) {
        toRoute<T>()
    } else null
}
