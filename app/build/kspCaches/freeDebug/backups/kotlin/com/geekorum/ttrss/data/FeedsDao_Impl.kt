package com.geekorum.ttrss.`data`

import android.database.Cursor
import android.os.CancellationSignal
import androidx.collection.LongSparseArray
import androidx.room.CoroutinesRoom
import androidx.room.CoroutinesRoom.Companion.execute
import androidx.room.EntityDeletionOrUpdateAdapter
import androidx.room.EntityInsertionAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.RoomSQLiteQuery.Companion.acquire
import androidx.room.SharedSQLiteStatement
import androidx.room.util.appendPlaceholders
import androidx.room.util.createCancellationSignal
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.newStringBuilder
import androidx.room.util.query
import androidx.room.util.recursiveFetchLongSparseArray
import androidx.room.withTransaction
import androidx.sqlite.db.SupportSQLiteStatement
import java.lang.Class
import java.lang.StringBuilder
import java.util.ArrayList
import java.util.concurrent.Callable
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.jvm.JvmStatic
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class FeedsDao_Impl(
  __db: RoomDatabase,
) : FeedsDao() {
  private val __db: RoomDatabase

  private val __insertionAdapterOfFeed: EntityInsertionAdapter<Feed>

  private val __insertionAdapterOfCategory: EntityInsertionAdapter<Category>

  private val __deletionAdapterOfFeed: EntityDeletionOrUpdateAdapter<Feed>

  private val __deletionAdapterOfCategory: EntityDeletionOrUpdateAdapter<Category>

  private val __preparedStmtOfDeleteArticleFromFeed: SharedSQLiteStatement

  private val __preparedStmtOfUpdateFeedUnreadCount: SharedSQLiteStatement

  private val __preparedStmtOfUpdateCategoryUnreadCount: SharedSQLiteStatement
  init {
    this.__db = __db
    this.__insertionAdapterOfFeed = object : EntityInsertionAdapter<Feed>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `feeds` (`_id`,`url`,`title`,`cat_id`,`display_title`,`last_time_update`,`unread_count`,`is_subscribed`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Feed) {
        statement.bindLong(1, entity.id)
        statement.bindString(2, entity.url)
        statement.bindString(3, entity.title)
        statement.bindLong(4, entity.catId)
        statement.bindString(5, entity.displayTitle)
        statement.bindLong(6, entity.lastTimeUpdate)
        statement.bindLong(7, entity.unreadCount.toLong())
        val _tmp: Int = if (entity.isSubscribed) 1 else 0
        statement.bindLong(8, _tmp.toLong())
      }
    }
    this.__insertionAdapterOfCategory = object : EntityInsertionAdapter<Category>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `categories` (`_id`,`title`,`unread_count`) VALUES (?,?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Category) {
        statement.bindLong(1, entity.id)
        statement.bindString(2, entity.title)
        statement.bindLong(3, entity.unreadCount.toLong())
      }
    }
    this.__deletionAdapterOfFeed = object : EntityDeletionOrUpdateAdapter<Feed>(__db) {
      protected override fun createQuery(): String = "DELETE FROM `feeds` WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Feed) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__deletionAdapterOfCategory = object : EntityDeletionOrUpdateAdapter<Category>(__db) {
      protected override fun createQuery(): String = "DELETE FROM `categories` WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Category) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__preparedStmtOfDeleteArticleFromFeed = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "DELETE FROM ARTICLES where feed_id=?"
        return _query
      }
    }
    this.__preparedStmtOfUpdateFeedUnreadCount = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE feeds SET unread_count=? WHERE _id=?"
        return _query
      }
    }
    this.__preparedStmtOfUpdateCategoryUnreadCount = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE categories SET unread_count=? WHERE _id=?"
        return _query
      }
    }
  }

  public override suspend fun insertFeeds(feeds: Collection<Feed>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfFeed.insert(feeds)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun insertCategories(categories: Collection<Category>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfCategory.insert(categories)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  internal override suspend fun deleteFeeds(feeds: Collection<Feed>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __deletionAdapterOfFeed.handleMultiple(feeds)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun deleteCategories(categories: Collection<Category>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __deletionAdapterOfCategory.handleMultiple(categories)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun deleteFeedsAndArticles(toBeDelete: List<Feed>) {
    __db.withTransaction {
      super@FeedsDao_Impl.deleteFeedsAndArticles(toBeDelete)
    }
  }

  public override suspend fun setFeedsAndCategories(feeds: Collection<Feed>,
      categories: Collection<Category>) {
    __db.withTransaction {
      super@FeedsDao_Impl.setFeedsAndCategories(feeds, categories)
    }
  }

  public override suspend fun updateFeedsAndCategoriesUnreadCount(feeds: Collection<Feed>,
      categories: Collection<Category>) {
    __db.withTransaction {
      super@FeedsDao_Impl.updateFeedsAndCategoriesUnreadCount(feeds, categories)
    }
  }

  internal override suspend fun deleteArticleFromFeed(feedId: Long): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfDeleteArticleFromFeed.acquire()
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, feedId)
      try {
        __db.beginTransaction()
        try {
          _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfDeleteArticleFromFeed.release(_stmt)
      }
    }
  })

  public override suspend fun updateFeedUnreadCount(id: Long, unreadCount: Int): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateFeedUnreadCount.acquire()
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, unreadCount.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, id)
      try {
        __db.beginTransaction()
        try {
          _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateFeedUnreadCount.release(_stmt)
      }
    }
  })

  public override suspend fun updateCategoryUnreadCount(id: Long, unreadCount: Int): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateCategoryUnreadCount.acquire()
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, unreadCount.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, id)
      try {
        __db.beginTransaction()
        try {
          _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateCategoryUnreadCount.release(_stmt)
      }
    }
  })

  public override fun getAllUnreadFeeds(): Flow<List<FeedWithFavIcon>> {
    val _sql: String = "SELECT * FROM feeds WHERE unread_count > 0 ORDER BY title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, true, arrayOf("feed_fav_icon", "feeds"), object :
        Callable<List<FeedWithFavIcon>> {
      public override fun call(): List<FeedWithFavIcon> {
        __db.beginTransaction()
        try {
          val _cursor: Cursor = query(__db, _statement, true, null)
          try {
            val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
            val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "url")
            val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
            val _cursorIndexOfCatId: Int = getColumnIndexOrThrow(_cursor, "cat_id")
            val _cursorIndexOfDisplayTitle: Int = getColumnIndexOrThrow(_cursor, "display_title")
            val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor,
                "last_time_update")
            val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
            val _cursorIndexOfIsSubscribed: Int = getColumnIndexOrThrow(_cursor, "is_subscribed")
            val _collectionFavIcon: LongSparseArray<FeedFavIcon?> = LongSparseArray<FeedFavIcon?>()
            while (_cursor.moveToNext()) {
              val _tmpKey: Long
              _tmpKey = _cursor.getLong(_cursorIndexOfId)
              _collectionFavIcon.put(_tmpKey, null)
            }
            _cursor.moveToPosition(-1)
            __fetchRelationshipfeedFavIconAscomGeekorumTtrssDataFeedFavIcon(_collectionFavIcon)
            val _result: MutableList<FeedWithFavIcon> =
                ArrayList<FeedWithFavIcon>(_cursor.getCount())
            while (_cursor.moveToNext()) {
              val _item: FeedWithFavIcon
              val _tmpFeed: Feed
              val _tmpId: Long
              _tmpId = _cursor.getLong(_cursorIndexOfId)
              val _tmpUrl: String
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
              val _tmpTitle: String
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
              val _tmpCatId: Long
              _tmpCatId = _cursor.getLong(_cursorIndexOfCatId)
              val _tmpDisplayTitle: String
              _tmpDisplayTitle = _cursor.getString(_cursorIndexOfDisplayTitle)
              val _tmpLastTimeUpdate: Long
              _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
              val _tmpUnreadCount: Int
              _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
              val _tmpIsSubscribed: Boolean
              val _tmp: Int
              _tmp = _cursor.getInt(_cursorIndexOfIsSubscribed)
              _tmpIsSubscribed = _tmp != 0
              _tmpFeed =
                  Feed(_tmpId,_tmpUrl,_tmpTitle,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount,_tmpIsSubscribed)
              val _tmpFavIcon: FeedFavIcon?
              val _tmpKey_1: Long
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId)
              _tmpFavIcon = _collectionFavIcon.get(_tmpKey_1)
              _item = FeedWithFavIcon(_tmpFeed,_tmpFavIcon)
              _result.add(_item)
            }
            __db.setTransactionSuccessful()
            return _result
          } finally {
            _cursor.close()
          }
        } finally {
          __db.endTransaction()
        }
      }

      protected fun finalize() {
        _statement.release()
      }
    })
  }

  public override fun getAllFeeds(): Flow<List<FeedWithFavIcon>> {
    val _sql: String = "SELECT * FROM feeds ORDER BY title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, true, arrayOf("feed_fav_icon", "feeds"), object :
        Callable<List<FeedWithFavIcon>> {
      public override fun call(): List<FeedWithFavIcon> {
        __db.beginTransaction()
        try {
          val _cursor: Cursor = query(__db, _statement, true, null)
          try {
            val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
            val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "url")
            val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
            val _cursorIndexOfCatId: Int = getColumnIndexOrThrow(_cursor, "cat_id")
            val _cursorIndexOfDisplayTitle: Int = getColumnIndexOrThrow(_cursor, "display_title")
            val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor,
                "last_time_update")
            val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
            val _cursorIndexOfIsSubscribed: Int = getColumnIndexOrThrow(_cursor, "is_subscribed")
            val _collectionFavIcon: LongSparseArray<FeedFavIcon?> = LongSparseArray<FeedFavIcon?>()
            while (_cursor.moveToNext()) {
              val _tmpKey: Long
              _tmpKey = _cursor.getLong(_cursorIndexOfId)
              _collectionFavIcon.put(_tmpKey, null)
            }
            _cursor.moveToPosition(-1)
            __fetchRelationshipfeedFavIconAscomGeekorumTtrssDataFeedFavIcon(_collectionFavIcon)
            val _result: MutableList<FeedWithFavIcon> =
                ArrayList<FeedWithFavIcon>(_cursor.getCount())
            while (_cursor.moveToNext()) {
              val _item: FeedWithFavIcon
              val _tmpFeed: Feed
              val _tmpId: Long
              _tmpId = _cursor.getLong(_cursorIndexOfId)
              val _tmpUrl: String
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
              val _tmpTitle: String
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
              val _tmpCatId: Long
              _tmpCatId = _cursor.getLong(_cursorIndexOfCatId)
              val _tmpDisplayTitle: String
              _tmpDisplayTitle = _cursor.getString(_cursorIndexOfDisplayTitle)
              val _tmpLastTimeUpdate: Long
              _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
              val _tmpUnreadCount: Int
              _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
              val _tmpIsSubscribed: Boolean
              val _tmp: Int
              _tmp = _cursor.getInt(_cursorIndexOfIsSubscribed)
              _tmpIsSubscribed = _tmp != 0
              _tmpFeed =
                  Feed(_tmpId,_tmpUrl,_tmpTitle,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount,_tmpIsSubscribed)
              val _tmpFavIcon: FeedFavIcon?
              val _tmpKey_1: Long
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId)
              _tmpFavIcon = _collectionFavIcon.get(_tmpKey_1)
              _item = FeedWithFavIcon(_tmpFeed,_tmpFavIcon)
              _result.add(_item)
            }
            __db.setTransactionSuccessful()
            return _result
          } finally {
            _cursor.close()
          }
        } finally {
          __db.endTransaction()
        }
      }

      protected fun finalize() {
        _statement.release()
      }
    })
  }

  internal override suspend fun getAllFeedsList(): List<Feed> {
    val _sql: String = "SELECT * FROM feeds"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<List<Feed>> {
      public override fun call(): List<Feed> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "url")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfCatId: Int = getColumnIndexOrThrow(_cursor, "cat_id")
          val _cursorIndexOfDisplayTitle: Int = getColumnIndexOrThrow(_cursor, "display_title")
          val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor, "last_time_update")
          val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
          val _cursorIndexOfIsSubscribed: Int = getColumnIndexOrThrow(_cursor, "is_subscribed")
          val _result: MutableList<Feed> = ArrayList<Feed>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: Feed
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpUrl: String
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpCatId: Long
            _tmpCatId = _cursor.getLong(_cursorIndexOfCatId)
            val _tmpDisplayTitle: String
            _tmpDisplayTitle = _cursor.getString(_cursorIndexOfDisplayTitle)
            val _tmpLastTimeUpdate: Long
            _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
            val _tmpUnreadCount: Int
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
            val _tmpIsSubscribed: Boolean
            val _tmp: Int
            _tmp = _cursor.getInt(_cursorIndexOfIsSubscribed)
            _tmpIsSubscribed = _tmp != 0
            _item =
                Feed(_tmpId,_tmpUrl,_tmpTitle,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount,_tmpIsSubscribed)
            _result.add(_item)
          }
          return _result
        } finally {
          _cursor.close()
          _statement.release()
        }
      }
    })
  }

  public override fun getAllCategories(): Flow<List<Category>> {
    val _sql: String = "SELECT * FROM categories ORDER BY title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("categories"), object :
        Callable<List<Category>> {
      public override fun call(): List<Category> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
          val _result: MutableList<Category> = ArrayList<Category>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: Category
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpUnreadCount: Int
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
            _item = Category(_tmpId,_tmpTitle,_tmpUnreadCount)
            _result.add(_item)
          }
          return _result
        } finally {
          _cursor.close()
        }
      }

      protected fun finalize() {
        _statement.release()
      }
    })
  }

  public override fun getAllUnreadCategories(): Flow<List<Category>> {
    val _sql: String = "SELECT * FROM categories WHERE unread_count > 0 ORDER BY title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("categories"), object :
        Callable<List<Category>> {
      public override fun call(): List<Category> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
          val _result: MutableList<Category> = ArrayList<Category>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: Category
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpUnreadCount: Int
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
            _item = Category(_tmpId,_tmpTitle,_tmpUnreadCount)
            _result.add(_item)
          }
          return _result
        } finally {
          _cursor.close()
        }
      }

      protected fun finalize() {
        _statement.release()
      }
    })
  }

  internal override suspend fun getAllCategoriesList(): List<Category> {
    val _sql: String = "SELECT * FROM categories"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<List<Category>> {
      public override fun call(): List<Category> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
          val _result: MutableList<Category> = ArrayList<Category>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: Category
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpUnreadCount: Int
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
            _item = Category(_tmpId,_tmpTitle,_tmpUnreadCount)
            _result.add(_item)
          }
          return _result
        } finally {
          _cursor.close()
          _statement.release()
        }
      }
    })
  }

  public override fun getFeedById(id: Long): Flow<Feed?> {
    val _sql: String = "SELECT * FROM feeds WHERE _id=?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, id)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("feeds"), object : Callable<Feed?> {
      public override fun call(): Feed? {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "url")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfCatId: Int = getColumnIndexOrThrow(_cursor, "cat_id")
          val _cursorIndexOfDisplayTitle: Int = getColumnIndexOrThrow(_cursor, "display_title")
          val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor, "last_time_update")
          val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
          val _cursorIndexOfIsSubscribed: Int = getColumnIndexOrThrow(_cursor, "is_subscribed")
          val _result: Feed?
          if (_cursor.moveToFirst()) {
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpUrl: String
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpCatId: Long
            _tmpCatId = _cursor.getLong(_cursorIndexOfCatId)
            val _tmpDisplayTitle: String
            _tmpDisplayTitle = _cursor.getString(_cursorIndexOfDisplayTitle)
            val _tmpLastTimeUpdate: Long
            _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
            val _tmpUnreadCount: Int
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
            val _tmpIsSubscribed: Boolean
            val _tmp: Int
            _tmp = _cursor.getInt(_cursorIndexOfIsSubscribed)
            _tmpIsSubscribed = _tmp != 0
            _result =
                Feed(_tmpId,_tmpUrl,_tmpTitle,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount,_tmpIsSubscribed)
          } else {
            _result = null
          }
          return _result
        } finally {
          _cursor.close()
        }
      }

      protected fun finalize() {
        _statement.release()
      }
    })
  }

  public override fun getUnreadFeedsForCategory(catId: Long): Flow<List<Feed>> {
    val _sql: String = "SELECT * FROM feeds WHERE unread_count > 0 AND cat_id=? ORDER BY title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, catId)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("feeds"), object : Callable<List<Feed>> {
      public override fun call(): List<Feed> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "url")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfCatId: Int = getColumnIndexOrThrow(_cursor, "cat_id")
          val _cursorIndexOfDisplayTitle: Int = getColumnIndexOrThrow(_cursor, "display_title")
          val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor, "last_time_update")
          val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
          val _cursorIndexOfIsSubscribed: Int = getColumnIndexOrThrow(_cursor, "is_subscribed")
          val _result: MutableList<Feed> = ArrayList<Feed>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: Feed
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpUrl: String
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpCatId: Long
            _tmpCatId = _cursor.getLong(_cursorIndexOfCatId)
            val _tmpDisplayTitle: String
            _tmpDisplayTitle = _cursor.getString(_cursorIndexOfDisplayTitle)
            val _tmpLastTimeUpdate: Long
            _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
            val _tmpUnreadCount: Int
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
            val _tmpIsSubscribed: Boolean
            val _tmp: Int
            _tmp = _cursor.getInt(_cursorIndexOfIsSubscribed)
            _tmpIsSubscribed = _tmp != 0
            _item =
                Feed(_tmpId,_tmpUrl,_tmpTitle,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount,_tmpIsSubscribed)
            _result.add(_item)
          }
          return _result
        } finally {
          _cursor.close()
        }
      }

      protected fun finalize() {
        _statement.release()
      }
    })
  }

  public override fun getFeedsForCategory(catId: Long): Flow<List<Feed>> {
    val _sql: String = "SELECT * FROM feeds WHERE cat_id=? ORDER BY title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, catId)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("feeds"), object : Callable<List<Feed>> {
      public override fun call(): List<Feed> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "url")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfCatId: Int = getColumnIndexOrThrow(_cursor, "cat_id")
          val _cursorIndexOfDisplayTitle: Int = getColumnIndexOrThrow(_cursor, "display_title")
          val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor, "last_time_update")
          val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(_cursor, "unread_count")
          val _cursorIndexOfIsSubscribed: Int = getColumnIndexOrThrow(_cursor, "is_subscribed")
          val _result: MutableList<Feed> = ArrayList<Feed>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: Feed
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpUrl: String
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpCatId: Long
            _tmpCatId = _cursor.getLong(_cursorIndexOfCatId)
            val _tmpDisplayTitle: String
            _tmpDisplayTitle = _cursor.getString(_cursorIndexOfDisplayTitle)
            val _tmpLastTimeUpdate: Long
            _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
            val _tmpUnreadCount: Int
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount)
            val _tmpIsSubscribed: Boolean
            val _tmp: Int
            _tmp = _cursor.getInt(_cursorIndexOfIsSubscribed)
            _tmpIsSubscribed = _tmp != 0
            _item =
                Feed(_tmpId,_tmpUrl,_tmpTitle,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount,_tmpIsSubscribed)
            _result.add(_item)
          }
          return _result
        } finally {
          _cursor.close()
        }
      }

      protected fun finalize() {
        _statement.release()
      }
    })
  }

  private
      fun __fetchRelationshipfeedFavIconAscomGeekorumTtrssDataFeedFavIcon(_map: LongSparseArray<FeedFavIcon?>) {
    if (_map.isEmpty()) {
      return
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      recursiveFetchLongSparseArray(_map, false) {
        __fetchRelationshipfeedFavIconAscomGeekorumTtrssDataFeedFavIcon(it)
      }
      return
    }
    val _stringBuilder: StringBuilder = newStringBuilder()
    _stringBuilder.append("SELECT `_id`,`url` FROM `feed_fav_icon` WHERE `_id` IN (")
    val _inputSize: Int = _map.size()
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _argCount: Int = 0 + _inputSize
    val _stmt: RoomSQLiteQuery = acquire(_sql, _argCount)
    var _argIndex: Int = 1
    for (i in 0 until _map.size()) {
      val _item: Long = _map.keyAt(i)
      _stmt.bindLong(_argIndex, _item)
      _argIndex++
    }
    val _cursor: Cursor = query(__db, _stmt, false, null)
    try {
      val _itemKeyIndex: Int = getColumnIndex(_cursor, "_id")
      if (_itemKeyIndex == -1) {
        return
      }
      val _cursorIndexOfId: Int = 0
      val _cursorIndexOfUrl: Int = 1
      while (_cursor.moveToNext()) {
        val _tmpKey: Long
        _tmpKey = _cursor.getLong(_itemKeyIndex)
        if (_map.containsKey(_tmpKey)) {
          val _item_1: FeedFavIcon?
          val _tmpId: Long
          _tmpId = _cursor.getLong(_cursorIndexOfId)
          val _tmpUrl: String
          _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
          _item_1 = FeedFavIcon(_tmpId,_tmpUrl)
          _map.put(_tmpKey, _item_1)
        }
      }
    } finally {
      _cursor.close()
    }
  }

  public companion object {
    @JvmStatic
    public fun getRequiredConverters(): List<Class<*>> = emptyList()
  }
}
