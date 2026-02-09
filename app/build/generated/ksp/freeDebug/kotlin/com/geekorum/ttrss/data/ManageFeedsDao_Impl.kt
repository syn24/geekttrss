package com.geekorum.ttrss.`data`

import android.database.Cursor
import androidx.collection.LongSparseArray
import androidx.paging.PagingSource
import androidx.room.AmbiguousColumnResolver
import androidx.room.CoroutinesRoom
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.RoomSQLiteQuery.Companion.acquire
import androidx.room.SharedSQLiteStatement
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.newStringBuilder
import androidx.room.util.query
import androidx.room.util.recursiveFetchLongSparseArray
import androidx.sqlite.db.SupportSQLiteStatement
import java.lang.Class
import java.lang.StringBuilder
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.concurrent.Callable
import javax.`annotation`.processing.Generated
import kotlin.Array
import kotlin.Boolean
import kotlin.Int
import kotlin.IntArray
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.jvm.JvmStatic
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class ManageFeedsDao_Impl(
  __db: RoomDatabase,
) : ManageFeedsDao {
  private val __db: RoomDatabase

  private val __preparedStmtOfUpdateIsSubscribedFeed: SharedSQLiteStatement
  init {
    this.__db = __db
    this.__preparedStmtOfUpdateIsSubscribedFeed = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE feeds SET is_subscribed=? WHERE _id=?"
        return _query
      }
    }
  }

  public override suspend fun updateIsSubscribedFeed(feedId: Long, isSubscribed: Boolean): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateIsSubscribedFeed.acquire()
      var _argIndex: Int = 1
      val _tmp: Int = if (isSubscribed) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 2
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
        __preparedStmtOfUpdateIsSubscribedFeed.release(_stmt)
      }
    }
  })

  public override fun getAllSubscribedFeeds(): PagingSource<Int, FeedWithFavIcon> {
    val _sql: String = "SELECT * FROM feeds WHERE is_subscribed == 1 ORDER BY title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<FeedWithFavIcon>(_statement, __db, "feed_fav_icon",
        "feeds") {
      protected override fun convertRows(cursor: Cursor): List<FeedWithFavIcon> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(cursor, "url")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfCatId: Int = getColumnIndexOrThrow(cursor, "cat_id")
        val _cursorIndexOfDisplayTitle: Int = getColumnIndexOrThrow(cursor, "display_title")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfUnreadCount: Int = getColumnIndexOrThrow(cursor, "unread_count")
        val _cursorIndexOfIsSubscribed: Int = getColumnIndexOrThrow(cursor, "is_subscribed")
        val _collectionFavIcon: LongSparseArray<FeedFavIcon?> = LongSparseArray<FeedFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfId)
          _collectionFavIcon.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedFavIconAscomGeekorumTtrssDataFeedFavIcon(_collectionFavIcon)
        val _result: MutableList<FeedWithFavIcon> = ArrayList<FeedWithFavIcon>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: FeedWithFavIcon
          val _tmpFeed: Feed
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpUrl: String
          _tmpUrl = cursor.getString(_cursorIndexOfUrl)
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpCatId: Long
          _tmpCatId = cursor.getLong(_cursorIndexOfCatId)
          val _tmpDisplayTitle: String
          _tmpDisplayTitle = cursor.getString(_cursorIndexOfDisplayTitle)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpUnreadCount: Int
          _tmpUnreadCount = cursor.getInt(_cursorIndexOfUnreadCount)
          val _tmpIsSubscribed: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsSubscribed)
          _tmpIsSubscribed = _tmp != 0
          _tmpFeed =
              Feed(_tmpId,_tmpUrl,_tmpTitle,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount,_tmpIsSubscribed)
          val _tmpFavIcon: FeedFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfId)
          _tmpFavIcon = _collectionFavIcon.get(_tmpKey_1)
          _item = FeedWithFavIcon(_tmpFeed,_tmpFavIcon)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getFeedById(id: Long): Flow<FeedWithFavIcon?> {
    val _sql: String = "SELECT * FROM feeds WHERE _id=?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, id)
    return CoroutinesRoom.createFlow(__db, true, arrayOf("feed_fav_icon", "feeds"), object :
        Callable<FeedWithFavIcon?> {
      public override fun call(): FeedWithFavIcon? {
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
            val _result: FeedWithFavIcon?
            if (_cursor.moveToFirst()) {
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
              _result = FeedWithFavIcon(_tmpFeed,_tmpFavIcon)
            } else {
              _result = null
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

  public override fun getSubscribedFeedsByCategories(): Flow<Map<Category, List<FeedWithFavIcon>>> {
    val _sql: String =
        "SELECT * FROM categories JOIN feeds on categories._id = feeds.cat_id WHERE feeds.is_subscribed == 1 ORDER BY categories.title, feeds.title"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, true, arrayOf("feed_fav_icon", "categories", "feeds"),
        object : Callable<Map<Category, List<FeedWithFavIcon>>> {
      public override fun call(): Map<Category, List<FeedWithFavIcon>> {
        __db.beginTransaction()
        try {
          val _cursor: Cursor = query(__db, _statement, true, null)
          try {
            val _cursorIndices: Array<IntArray> =
                AmbiguousColumnResolver.resolve(_cursor.getColumnNames(), arrayOf(arrayOf("_id",
                "title", "unread_count"), arrayOf("_id", "title", "unread_count", "url", "cat_id",
                "display_title", "last_time_update", "is_subscribed")))
            val _collectionFavIcon: LongSparseArray<FeedFavIcon?> = LongSparseArray<FeedFavIcon?>()
            while (_cursor.moveToNext()) {
              val _tmpKey: Long
              _tmpKey = _cursor.getLong(_cursorIndices[1][0])
              _collectionFavIcon.put(_tmpKey, null)
            }
            _cursor.moveToPosition(-1)
            __fetchRelationshipfeedFavIconAscomGeekorumTtrssDataFeedFavIcon(_collectionFavIcon)
            val _result: MutableMap<Category, MutableList<FeedWithFavIcon>> =
                LinkedHashMap<Category, MutableList<FeedWithFavIcon>>()
            while (_cursor.moveToNext()) {
              val _key: Category
              val _tmpId: Long
              _tmpId = _cursor.getLong(_cursorIndices[0][0])
              val _tmpTitle: String
              _tmpTitle = _cursor.getString(_cursorIndices[0][1])
              val _tmpUnreadCount: Int
              _tmpUnreadCount = _cursor.getInt(_cursorIndices[0][2])
              _key = Category(_tmpId,_tmpTitle,_tmpUnreadCount)
              val _values: MutableList<FeedWithFavIcon>
              if (_result.containsKey(_key)) {
                _values = _result.getValue(_key)
              } else {
                _values = ArrayList<FeedWithFavIcon>()
                _result.put(_key, _values)
              }
              if (_cursor.isNull(_cursorIndices[1][0]) && _cursor.isNull(_cursorIndices[1][1]) &&
                  _cursor.isNull(_cursorIndices[1][2]) && _cursor.isNull(_cursorIndices[1][3]) &&
                  _cursor.isNull(_cursorIndices[1][4]) && _cursor.isNull(_cursorIndices[1][5]) &&
                  _cursor.isNull(_cursorIndices[1][6]) && _cursor.isNull(_cursorIndices[1][7])) {
                continue
              }
              val _value: FeedWithFavIcon
              val _tmpFeed: Feed
              val _tmpId_1: Long
              _tmpId_1 = _cursor.getLong(_cursorIndices[1][0])
              val _tmpTitle_1: String
              _tmpTitle_1 = _cursor.getString(_cursorIndices[1][1])
              val _tmpUnreadCount_1: Int
              _tmpUnreadCount_1 = _cursor.getInt(_cursorIndices[1][2])
              val _tmpUrl: String
              _tmpUrl = _cursor.getString(_cursorIndices[1][3])
              val _tmpCatId: Long
              _tmpCatId = _cursor.getLong(_cursorIndices[1][4])
              val _tmpDisplayTitle: String
              _tmpDisplayTitle = _cursor.getString(_cursorIndices[1][5])
              val _tmpLastTimeUpdate: Long
              _tmpLastTimeUpdate = _cursor.getLong(_cursorIndices[1][6])
              val _tmpIsSubscribed: Boolean
              val _tmp: Int
              _tmp = _cursor.getInt(_cursorIndices[1][7])
              _tmpIsSubscribed = _tmp != 0
              _tmpFeed =
                  Feed(_tmpId_1,_tmpUrl,_tmpTitle_1,_tmpCatId,_tmpDisplayTitle,_tmpLastTimeUpdate,_tmpUnreadCount_1,_tmpIsSubscribed)
              val _tmpFavIcon: FeedFavIcon?
              val _tmpKey_1: Long
              _tmpKey_1 = _cursor.getLong(_cursorIndices[1][0])
              _tmpFavIcon = _collectionFavIcon.get(_tmpKey_1)
              _value = FeedWithFavIcon(_tmpFeed,_tmpFavIcon)
              _values.add(_value)
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
