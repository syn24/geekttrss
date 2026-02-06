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
package com.geekorum.ttrss.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * Dao to access Feeds and Categories
 */
@Dao
abstract class FeedsDao {

    @Query("SELECT * FROM feeds WHERE unread_count > 0 ORDER BY title")
    @Transaction
    abstract fun getAllUnreadFeeds(): Flow<List<FeedWithFavIcon>>

    @Query("SELECT * FROM feeds ORDER BY title")
    @Transaction
    abstract fun getAllFeeds(): Flow<List<FeedWithFavIcon>>

    @Query("SELECT * FROM feeds")
    internal abstract suspend fun getAllFeedsList(): List<Feed>

    @Query("SELECT * FROM categories ORDER BY title")
    abstract fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE unread_count > 0 ORDER BY title")
    abstract fun getAllUnreadCategories(): Flow<List<Category>>

    @Query("SELECT * FROM categories")
    internal abstract suspend fun getAllCategoriesList(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFeeds(feeds: Collection<Feed>)

    @Delete
    internal abstract suspend fun deleteFeeds(feeds: Collection<Feed>)

    @Query("DELETE FROM ARTICLES where feed_id=:feedId")
    internal abstract suspend fun deleteArticleFromFeed(feedId: Long)

    @Transaction
    open suspend fun deleteFeedsAndArticles(toBeDelete: List<Feed>) {
        for ((id) in toBeDelete) {
            deleteArticleFromFeed(id)
        }
        deleteFeeds(toBeDelete)
    }


    @Query("SELECT * FROM feeds WHERE _id=:id")
    abstract fun getFeedById(id: Long): Flow<Feed?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCategories(categories: Collection<Category>)

    @Delete
    abstract suspend fun deleteCategories(categories: Collection<Category>)

    @Query("SELECT * FROM feeds WHERE unread_count > 0 AND cat_id=:catId ORDER BY title")
    abstract fun getUnreadFeedsForCategory(catId: Long): Flow<List<Feed>>

    @Query("SELECT * FROM feeds WHERE cat_id=:catId ORDER BY title")
    abstract fun getFeedsForCategory(catId: Long): Flow<List<Feed>>

    @Transaction
    open suspend fun setFeedsAndCategories(feeds: Collection<Feed>, categories: Collection<Category>) {
        setCategories(categories)
        setFeeds(feeds)
    }

    private suspend fun setFeeds(feeds: Collection<Feed>) {
        val feedsIds: List<Long> = feeds.map { it.id }
        val toDelete = getAllFeedsList().filter { it.id !in feedsIds }

        deleteFeedsAndArticles(toDelete)
        insertFeeds(feeds)
    }

    private suspend fun setCategories(categories: Collection<Category>) {
        val categoriesIds: List<Long> = categories.map { category -> category.id }
        val toDelete = getAllCategoriesList().filter { it.id !in categoriesIds }
        deleteCategories(toDelete)
        insertCategories(categories)
    }

    @Query("UPDATE feeds SET unread_count=:unreadCount WHERE _id=:id")
    abstract suspend fun updateFeedUnreadCount(id: Long, unreadCount: Int)

    @Query("UPDATE categories SET unread_count=:unreadCount WHERE _id=:id")
    abstract suspend fun updateCategoryUnreadCount(id: Long, unreadCount: Int)


    @Transaction
    open suspend fun updateFeedsAndCategoriesUnreadCount(
        feeds: Collection<Feed>, categories: Collection<Category>
    ) {
        internalUpdateCategoriesUnreadCount(categories)
        internalUpdateFeedsUnreadCount(feeds)
    }

    private suspend fun internalUpdateFeedsUnreadCount(feeds: Collection<Feed>) {
        if (feeds.isEmpty()) return

        val feedsIds = feeds.map { it.id }.toSet()
        val currentFeeds = getAllFeedsList()
        val currentFeedsIds = currentFeeds.map { it.id }.toSet()

        // Efficiently categorize feeds
        val toInsert = feeds.filter { it.id !in currentFeedsIds }
        val toUpdate = feeds.filter { it.id in currentFeedsIds }
        val toSetZero = currentFeeds.filter { it.id !in feedsIds }

        // Batch operations
        if (toSetZero.isNotEmpty()) {
            setFeedsUnreadCountTo0(toSetZero)
        }
        if (toUpdate.isNotEmpty()) {
            updateFeedsUnreadCount(toUpdate)
        }
        if (toInsert.isNotEmpty()) {
            insertFeeds(toInsert)
        }
    }

    private suspend fun updateFeedsUnreadCount(feeds: Collection<Feed>) {
        // Batch updates to reduce transaction overhead
        feeds.chunked(100).forEach { batch ->
            batch.forEach {
                updateFeedUnreadCount(it.id, it.unreadCount)
            }
        }
    }

    private suspend fun setFeedsUnreadCountTo0(feeds: List<Feed>) {
        // Batch updates to reduce transaction overhead
        feeds.chunked(100).forEach { batch ->
            batch.forEach {
                updateFeedUnreadCount(it.id, 0)
            }
        }
    }

    private suspend fun internalUpdateCategoriesUnreadCount(categories: Collection<Category>) {
        if (categories.isEmpty()) return

        val categoriesIds = categories.map { it.id }.toSet()
        val currentCategories = getAllCategoriesList()
        val currentCategoriesIds = currentCategories.map { it.id }.toSet()

        // Efficiently categorize categories
        val toInsert = categories.filter { it.id !in currentCategoriesIds }
        val toUpdate = categories.filter { it.id in currentCategoriesIds }
        val toSetZero = currentCategories.filter { it.id !in categoriesIds }

        // Batch operations
        if (toSetZero.isNotEmpty()) {
            setCategoriesUnreadCountTo0(toSetZero)
        }
        if (toUpdate.isNotEmpty()) {
            updateCategoriesUnreadCount(toUpdate)
        }
        if (toInsert.isNotEmpty()) {
            insertCategories(toInsert)
        }
    }

    private suspend fun updateCategoriesUnreadCount(categories: Collection<Category>) {
        // Batch updates to reduce transaction overhead
        categories.chunked(100).forEach { batch ->
            batch.forEach {
                updateCategoryUnreadCount(it.id, it.unreadCount)
            }
        }
    }

    private suspend fun setCategoriesUnreadCountTo0(categories: Collection<Category>) {
        // Batch updates to reduce transaction overhead
        categories.chunked(100).forEach { batch ->
            batch.forEach {
                updateCategoryUnreadCount(it.id, 0)
            }
        }
    }
}
