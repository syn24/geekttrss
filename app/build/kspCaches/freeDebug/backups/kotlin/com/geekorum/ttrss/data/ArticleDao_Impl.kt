package com.geekorum.ttrss.`data`

import android.database.Cursor
import android.os.CancellationSignal
import androidx.collection.LongSparseArray
import androidx.paging.PagingSource
import androidx.room.CoroutinesRoom
import androidx.room.CoroutinesRoom.Companion.execute
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.RoomSQLiteQuery.Companion.acquire
import androidx.room.SharedSQLiteStatement
import androidx.room.paging.LimitOffsetPagingSource
import androidx.room.util.appendPlaceholders
import androidx.room.util.createCancellationSignal
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.newStringBuilder
import androidx.room.util.query
import androidx.room.util.recursiveFetchLongSparseArray
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
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.jvm.JvmStatic
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class ArticleDao_Impl(
  __db: RoomDatabase,
) : ArticleDao {
  private val __db: RoomDatabase

  private val __preparedStmtOfUpdateArticleTransientUnread: SharedSQLiteStatement

  private val __preparedStmtOfUpdateArticleUnread: SharedSQLiteStatement

  private val __preparedStmtOfUpdateAllArticleUnread: SharedSQLiteStatement

  private val __preparedStmtOfUpdateArticleUnreadForFeed: SharedSQLiteStatement

  private val __preparedStmtOfUpdateArticleMarked: SharedSQLiteStatement

  private val __preparedStmtOfUpdateArticlePublished: SharedSQLiteStatement
  init {
    this.__db = __db
    this.__preparedStmtOfUpdateArticleTransientUnread = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE articles SET transiant_unread=? WHERE _id=?"
        return _query
      }
    }
    this.__preparedStmtOfUpdateArticleUnread = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE articles SET transiant_unread=?, unread=? WHERE _id=?"
        return _query
      }
    }
    this.__preparedStmtOfUpdateAllArticleUnread = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE articles SET transiant_unread=?, unread=?"
        return _query
      }
    }
    this.__preparedStmtOfUpdateArticleUnreadForFeed = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE articles SET transiant_unread=?, unread=? WHERE feed_id=?"
        return _query
      }
    }
    this.__preparedStmtOfUpdateArticleMarked = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE articles SET marked=? WHERE _id=?"
        return _query
      }
    }
    this.__preparedStmtOfUpdateArticlePublished = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "UPDATE articles SET published=? WHERE _id=?"
        return _query
      }
    }
  }

  public override suspend fun updateArticleTransientUnread(articleId: Long, isUnread: Boolean): Unit
      = CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateArticleTransientUnread.acquire()
      var _argIndex: Int = 1
      val _tmp: Int = if (isUnread) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, articleId)
      try {
        __db.beginTransaction()
        try {
          _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateArticleTransientUnread.release(_stmt)
      }
    }
  })

  public override suspend fun updateArticleUnread(articleId: Long, isUnread: Boolean): Int =
      CoroutinesRoom.execute(__db, true, object : Callable<Int> {
    public override fun call(): Int {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateArticleUnread.acquire()
      var _argIndex: Int = 1
      val _tmp: Int = if (isUnread) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 2
      val _tmp_1: Int = if (isUnread) 1 else 0
      _stmt.bindLong(_argIndex, _tmp_1.toLong())
      _argIndex = 3
      _stmt.bindLong(_argIndex, articleId)
      try {
        __db.beginTransaction()
        try {
          val _result: Int = _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
          return _result
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateArticleUnread.release(_stmt)
      }
    }
  })

  public override suspend fun updateAllArticleUnread(isUnread: Boolean): Int =
      CoroutinesRoom.execute(__db, true, object : Callable<Int> {
    public override fun call(): Int {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateAllArticleUnread.acquire()
      var _argIndex: Int = 1
      val _tmp: Int = if (isUnread) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 2
      val _tmp_1: Int = if (isUnread) 1 else 0
      _stmt.bindLong(_argIndex, _tmp_1.toLong())
      try {
        __db.beginTransaction()
        try {
          val _result: Int = _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
          return _result
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateAllArticleUnread.release(_stmt)
      }
    }
  })

  public override suspend fun updateArticleUnreadForFeed(feedId: Long, isUnread: Boolean): Int =
      CoroutinesRoom.execute(__db, true, object : Callable<Int> {
    public override fun call(): Int {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateArticleUnreadForFeed.acquire()
      var _argIndex: Int = 1
      val _tmp: Int = if (isUnread) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 2
      val _tmp_1: Int = if (isUnread) 1 else 0
      _stmt.bindLong(_argIndex, _tmp_1.toLong())
      _argIndex = 3
      _stmt.bindLong(_argIndex, feedId)
      try {
        __db.beginTransaction()
        try {
          val _result: Int = _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
          return _result
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateArticleUnreadForFeed.release(_stmt)
      }
    }
  })

  public override suspend fun updateArticleMarked(articleId: Long, isMarked: Boolean): Int =
      CoroutinesRoom.execute(__db, true, object : Callable<Int> {
    public override fun call(): Int {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateArticleMarked.acquire()
      var _argIndex: Int = 1
      val _tmp: Int = if (isMarked) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, articleId)
      try {
        __db.beginTransaction()
        try {
          val _result: Int = _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
          return _result
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateArticleMarked.release(_stmt)
      }
    }
  })

  public override suspend fun updateArticlePublished(articleId: Long, isPublished: Boolean): Int =
      CoroutinesRoom.execute(__db, true, object : Callable<Int> {
    public override fun call(): Int {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfUpdateArticlePublished.acquire()
      var _argIndex: Int = 1
      val _tmp: Int = if (isPublished) 1 else 0
      _stmt.bindLong(_argIndex, _tmp.toLong())
      _argIndex = 2
      _stmt.bindLong(_argIndex, articleId)
      try {
        __db.beginTransaction()
        try {
          val _result: Int = _stmt.executeUpdateDelete()
          __db.setTransactionSuccessful()
          return _result
        } finally {
          __db.endTransaction()
        }
      } finally {
        __preparedStmtOfUpdateArticlePublished.release(_stmt)
      }
    }
  })

  public override fun getArticleById(id: Long): Flow<Article?> {
    val _sql: String = "SELECT * FROM articles WHERE _id=?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, id)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("articles"), object : Callable<Article?> {
      public override fun call(): Article? {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(_cursor, "unread")
          val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(_cursor,
              "transiant_unread")
          val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(_cursor, "marked")
          val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(_cursor, "published")
          val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_cursor, "score")
          val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor, "last_time_update")
          val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(_cursor, "is_updated")
          val _cursorIndexOfLink: Int = getColumnIndexOrThrow(_cursor, "link")
          val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(_cursor, "feed_id")
          val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(_cursor, "flavor_image_uri")
          val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(_cursor, "content_excerpt")
          val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
          val _cursorIndexOfTags: Int = getColumnIndexOrThrow(_cursor, "tags")
          val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_cursor, "content")
          val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(_cursor, "author")
          val _result: Article?
          if (_cursor.moveToFirst()) {
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpIsUnread: Boolean
            val _tmp: Int
            _tmp = _cursor.getInt(_cursorIndexOfIsUnread)
            _tmpIsUnread = _tmp != 0
            val _tmpIsTransientUnread: Boolean
            val _tmp_1: Int
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsTransientUnread)
            _tmpIsTransientUnread = _tmp_1 != 0
            val _tmpIsStarred: Boolean
            val _tmp_2: Int
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsStarred)
            _tmpIsStarred = _tmp_2 != 0
            val _tmpIsPublished: Boolean
            val _tmp_3: Int
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsPublished)
            _tmpIsPublished = _tmp_3 != 0
            val _tmpScore: Int
            _tmpScore = _cursor.getInt(_cursorIndexOfScore)
            val _tmpLastTimeUpdate: Long
            _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
            val _tmpIsUpdated: Boolean
            val _tmp_4: Int
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsUpdated)
            _tmpIsUpdated = _tmp_4 != 0
            val _tmpLink: String
            _tmpLink = _cursor.getString(_cursorIndexOfLink)
            val _tmpFeedId: Long
            _tmpFeedId = _cursor.getLong(_cursorIndexOfFeedId)
            val _tmpFlavorImageUri: String
            _tmpFlavorImageUri = _cursor.getString(_cursorIndexOfFlavorImageUri)
            val _tmpContentExcerpt: String
            _tmpContentExcerpt = _cursor.getString(_cursorIndexOfContentExcerpt)
            val _tmpContentData: ArticleContentIndexed
            val _tmpTitle: String
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
            val _tmpTags: String
            _tmpTags = _cursor.getString(_cursorIndexOfTags)
            val _tmpContent: String
            _tmpContent = _cursor.getString(_cursorIndexOfContent)
            val _tmpAuthor: String
            _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor)
            _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
            _result =
                Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
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

  public override fun getArticlesById(articleIds: List<Long>): PagingSource<Int, ArticleWithFeed> {
    val _stringBuilder: StringBuilder = newStringBuilder()
    _stringBuilder.append("SELECT * FROM articles WHERE _id IN (")
    val _inputSize: Int = articleIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _argCount: Int = 0 + _inputSize
    val _statement: RoomSQLiteQuery = acquire(_sql, _argCount)
    var _argIndex: Int = 1
    for (_item: Long in articleIds) {
      _statement.bindLong(_argIndex, _item)
      _argIndex++
    }
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item_1: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item_1 = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item_1)
        }
        return _result
      }
    }
  }

  public override fun getAllArticles(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllArticlesOldestFirst(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticles(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE unread=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticlesOldestFirst(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE unread=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override suspend fun getUnreadArticlesRandomized(count: Int): List<ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE unread=1 ORDER BY RANDOM() LIMIT ?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, count.toLong())
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, true, _cancellationSignal, object : Callable<List<ArticleWithFeed>> {
      public override fun call(): List<ArticleWithFeed> {
        __db.beginTransaction()
        try {
          val _cursor: Cursor = query(__db, _statement, true, null)
          try {
            val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
            val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(_cursor, "unread")
            val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(_cursor,
                "transiant_unread")
            val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(_cursor, "marked")
            val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(_cursor, "published")
            val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_cursor, "score")
            val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor,
                "last_time_update")
            val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(_cursor, "is_updated")
            val _cursorIndexOfLink: Int = getColumnIndexOrThrow(_cursor, "link")
            val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(_cursor, "feed_id")
            val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(_cursor,
                "flavor_image_uri")
            val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(_cursor,
                "content_excerpt")
            val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
            val _cursorIndexOfTags: Int = getColumnIndexOrThrow(_cursor, "tags")
            val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_cursor, "content")
            val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(_cursor, "author")
            val _collectionFeed: LongSparseArray<FeedWithFavIcon?> =
                LongSparseArray<FeedWithFavIcon?>()
            while (_cursor.moveToNext()) {
              val _tmpKey: Long
              _tmpKey = _cursor.getLong(_cursorIndexOfFeedId)
              _collectionFeed.put(_tmpKey, null)
            }
            _cursor.moveToPosition(-1)
            __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
            val _result: MutableList<ArticleWithFeed> =
                ArrayList<ArticleWithFeed>(_cursor.getCount())
            while (_cursor.moveToNext()) {
              val _item: ArticleWithFeed
              val _tmpArticle: Article
              val _tmpId: Long
              _tmpId = _cursor.getLong(_cursorIndexOfId)
              val _tmpIsUnread: Boolean
              val _tmp: Int
              _tmp = _cursor.getInt(_cursorIndexOfIsUnread)
              _tmpIsUnread = _tmp != 0
              val _tmpIsTransientUnread: Boolean
              val _tmp_1: Int
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsTransientUnread)
              _tmpIsTransientUnread = _tmp_1 != 0
              val _tmpIsStarred: Boolean
              val _tmp_2: Int
              _tmp_2 = _cursor.getInt(_cursorIndexOfIsStarred)
              _tmpIsStarred = _tmp_2 != 0
              val _tmpIsPublished: Boolean
              val _tmp_3: Int
              _tmp_3 = _cursor.getInt(_cursorIndexOfIsPublished)
              _tmpIsPublished = _tmp_3 != 0
              val _tmpScore: Int
              _tmpScore = _cursor.getInt(_cursorIndexOfScore)
              val _tmpLastTimeUpdate: Long
              _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
              val _tmpIsUpdated: Boolean
              val _tmp_4: Int
              _tmp_4 = _cursor.getInt(_cursorIndexOfIsUpdated)
              _tmpIsUpdated = _tmp_4 != 0
              val _tmpLink: String
              _tmpLink = _cursor.getString(_cursorIndexOfLink)
              val _tmpFeedId: Long
              _tmpFeedId = _cursor.getLong(_cursorIndexOfFeedId)
              val _tmpFlavorImageUri: String
              _tmpFlavorImageUri = _cursor.getString(_cursorIndexOfFlavorImageUri)
              val _tmpContentExcerpt: String
              _tmpContentExcerpt = _cursor.getString(_cursorIndexOfContentExcerpt)
              val _tmpContentData: ArticleContentIndexed
              val _tmpTitle: String
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
              val _tmpTags: String
              _tmpTags = _cursor.getString(_cursorIndexOfTags)
              val _tmpContent: String
              _tmpContent = _cursor.getString(_cursorIndexOfContent)
              val _tmpAuthor: String
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor)
              _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
              _tmpArticle =
                  Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
              val _tmpFeed: FeedWithFavIcon?
              val _tmpKey_1: Long
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfFeedId)
              _tmpFeed = _collectionFeed.get(_tmpKey_1)
              if (_tmpFeed == null) {
                error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
              }
              _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
              _result.add(_item)
            }
            __db.setTransactionSuccessful()
            return _result
          } finally {
            _cursor.close()
            _statement.release()
          }
        } finally {
          __db.endTransaction()
        }
      }
    })
  }

  public override fun getAllArticlesForFeed(feedId: Long): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE feed_id=? ORDER BY last_time_update DESC "
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, feedId)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllArticlesForFeedOldestFirst(feedId: Long):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE feed_id=? ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, feedId)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticlesForFeed(feedId: Long):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE feed_id=? AND unread=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, feedId)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticlesForFeedOldestFirst(feedId: Long):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE feed_id=? AND unread=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, feedId)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override suspend fun getAllUnreadArticlesForFeedUpdatedAfterTimeRandomized(feedId: Long,
      time: Long): List<Article> {
    val _sql: String =
        "SELECT * FROM articles WHERE last_time_update>=? AND unread=1 AND feed_id=? ORDER BY RANDOM()"
    val _statement: RoomSQLiteQuery = acquire(_sql, 2)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, time)
    _argIndex = 2
    _statement.bindLong(_argIndex, feedId)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, true, _cancellationSignal, object : Callable<List<Article>> {
      public override fun call(): List<Article> {
        __db.beginTransaction()
        try {
          val _cursor: Cursor = query(__db, _statement, false, null)
          try {
            val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
            val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(_cursor, "unread")
            val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(_cursor,
                "transiant_unread")
            val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(_cursor, "marked")
            val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(_cursor, "published")
            val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_cursor, "score")
            val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor,
                "last_time_update")
            val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(_cursor, "is_updated")
            val _cursorIndexOfLink: Int = getColumnIndexOrThrow(_cursor, "link")
            val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(_cursor, "feed_id")
            val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(_cursor,
                "flavor_image_uri")
            val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(_cursor,
                "content_excerpt")
            val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
            val _cursorIndexOfTags: Int = getColumnIndexOrThrow(_cursor, "tags")
            val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_cursor, "content")
            val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(_cursor, "author")
            val _result: MutableList<Article> = ArrayList<Article>(_cursor.getCount())
            while (_cursor.moveToNext()) {
              val _item: Article
              val _tmpId: Long
              _tmpId = _cursor.getLong(_cursorIndexOfId)
              val _tmpIsUnread: Boolean
              val _tmp: Int
              _tmp = _cursor.getInt(_cursorIndexOfIsUnread)
              _tmpIsUnread = _tmp != 0
              val _tmpIsTransientUnread: Boolean
              val _tmp_1: Int
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsTransientUnread)
              _tmpIsTransientUnread = _tmp_1 != 0
              val _tmpIsStarred: Boolean
              val _tmp_2: Int
              _tmp_2 = _cursor.getInt(_cursorIndexOfIsStarred)
              _tmpIsStarred = _tmp_2 != 0
              val _tmpIsPublished: Boolean
              val _tmp_3: Int
              _tmp_3 = _cursor.getInt(_cursorIndexOfIsPublished)
              _tmpIsPublished = _tmp_3 != 0
              val _tmpScore: Int
              _tmpScore = _cursor.getInt(_cursorIndexOfScore)
              val _tmpLastTimeUpdate: Long
              _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
              val _tmpIsUpdated: Boolean
              val _tmp_4: Int
              _tmp_4 = _cursor.getInt(_cursorIndexOfIsUpdated)
              _tmpIsUpdated = _tmp_4 != 0
              val _tmpLink: String
              _tmpLink = _cursor.getString(_cursorIndexOfLink)
              val _tmpFeedId: Long
              _tmpFeedId = _cursor.getLong(_cursorIndexOfFeedId)
              val _tmpFlavorImageUri: String
              _tmpFlavorImageUri = _cursor.getString(_cursorIndexOfFlavorImageUri)
              val _tmpContentExcerpt: String
              _tmpContentExcerpt = _cursor.getString(_cursorIndexOfContentExcerpt)
              val _tmpContentData: ArticleContentIndexed
              val _tmpTitle: String
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
              val _tmpTags: String
              _tmpTags = _cursor.getString(_cursorIndexOfTags)
              val _tmpContent: String
              _tmpContent = _cursor.getString(_cursorIndexOfContent)
              val _tmpAuthor: String
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor)
              _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
              _item =
                  Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
              _result.add(_item)
            }
            __db.setTransactionSuccessful()
            return _result
          } finally {
            _cursor.close()
            _statement.release()
          }
        } finally {
          __db.endTransaction()
        }
      }
    })
  }

  public override fun getAllArticlesForTag(tag: String): PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT articles.* FROM articles  JOIN articles_tags ON (articles_tags.article_id = articles._id) WHERE articles_tags.tag=? ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindString(_argIndex, tag)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles", "articles_tags") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllArticlesForTagOldestFirst(tag: String):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT articles.* FROM articles  JOIN articles_tags ON (articles_tags.article_id = articles._id) WHERE articles_tags.tag=? ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindString(_argIndex, tag)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles", "articles_tags") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticlesForTag(tag: String): PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT articles.* FROM articles  JOIN articles_tags ON (articles_tags.article_id = articles._id) WHERE articles_tags.tag=? AND unread=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindString(_argIndex, tag)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles", "articles_tags") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticlesForTagOldestFirst(tag: String):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT articles.* FROM articles  JOIN articles_tags ON (articles_tags.article_id = articles._id) WHERE articles_tags.tag=? AND unread=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindString(_argIndex, tag)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles", "articles_tags") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllStarredArticles(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE marked=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllStarredArticlesOldestFirst(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE marked=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadStarredArticles(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE marked=1 AND unread=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadStarredArticlesOldestFirst(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE marked=1 AND unread=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllPublishedArticles(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE published=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllPublishedArticlesOldestFirst(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE published=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadPublishedArticles(): PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE published=1 AND unread=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadPublishedArticlesOldestFirst():
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE published=1 AND unread=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllArticlesUpdatedAfterTime(time: Long):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE last_time_update>=? ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, time)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllArticlesUpdatedAfterTimeOldestFirst(time: Long):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String = "SELECT * FROM articles WHERE last_time_update>=? ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, time)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticlesUpdatedAfterTime(time: Long):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE last_time_update>=? AND unread=1 ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, time)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getAllUnreadArticlesUpdatedAfterTimeOldestFirst(time: Long):
      PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT * FROM articles WHERE last_time_update>=? AND unread=1 ORDER BY last_time_update"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, time)
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override fun getUnreadArticlesUpdatedAfterTimeCount(time: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM articles WHERE last_time_update>=? AND unread=1"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, time)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("articles"), object : Callable<Int> {
      public override fun call(): Int {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _result: Int
          if (_cursor.moveToFirst()) {
            val _tmp: Int
            _tmp = _cursor.getInt(0)
            _result = _tmp
          } else {
            _result = 0
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

  public override fun getAllArticlesCount(): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM articles"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("articles"), object : Callable<Int> {
      public override fun call(): Int {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _result: Int
          if (_cursor.moveToFirst()) {
            val _tmp: Int
            _tmp = _cursor.getInt(0)
            _result = _tmp
          } else {
            _result = 0
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

  public override fun getAllUnreadArticlesCount(): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM articles WHERE unread=1"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("articles"), object : Callable<Int> {
      public override fun call(): Int {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _result: Int
          if (_cursor.moveToFirst()) {
            val _tmp: Int
            _tmp = _cursor.getInt(0)
            _result = _tmp
          } else {
            _result = 0
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

  public override fun getAllStarredArticlesCount(): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM articles WHERE marked=1"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    return CoroutinesRoom.createFlow(__db, false, arrayOf("articles"), object : Callable<Int> {
      public override fun call(): Int {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _result: Int
          if (_cursor.moveToFirst()) {
            val _tmp: Int
            _tmp = _cursor.getInt(0)
            _result = _tmp
          } else {
            _result = 0
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

  public override fun searchArticles(query: String?): PagingSource<Int, ArticleWithFeed> {
    val _sql: String =
        "SELECT articles.* FROM ArticleFTS JOIN articles ON (ArticleFTS.rowid = _id) WHERE ArticleFTS MATCH ? ORDER BY last_time_update DESC"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    if (query == null) {
      _statement.bindNull(_argIndex)
    } else {
      _statement.bindString(_argIndex, query)
    }
    return object : LimitOffsetPagingSource<ArticleWithFeed>(_statement, __db, "feed_fav_icon",
        "feeds", "ArticleFTS", "articles") {
      protected override fun convertRows(cursor: Cursor): List<ArticleWithFeed> {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(cursor, "_id")
        val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(cursor, "unread")
        val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(cursor, "transiant_unread")
        val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(cursor, "marked")
        val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(cursor, "published")
        val _cursorIndexOfScore: Int = getColumnIndexOrThrow(cursor, "score")
        val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(cursor, "last_time_update")
        val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(cursor, "is_updated")
        val _cursorIndexOfLink: Int = getColumnIndexOrThrow(cursor, "link")
        val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(cursor, "feed_id")
        val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(cursor, "flavor_image_uri")
        val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(cursor, "content_excerpt")
        val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(cursor, "title")
        val _cursorIndexOfTags: Int = getColumnIndexOrThrow(cursor, "tags")
        val _cursorIndexOfContent: Int = getColumnIndexOrThrow(cursor, "content")
        val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(cursor, "author")
        val _collectionFeed: LongSparseArray<FeedWithFavIcon?> = LongSparseArray<FeedWithFavIcon?>()
        while (cursor.moveToNext()) {
          val _tmpKey: Long
          _tmpKey = cursor.getLong(_cursorIndexOfFeedId)
          _collectionFeed.put(_tmpKey, null)
        }
        cursor.moveToPosition(-1)
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
        val _result: MutableList<ArticleWithFeed> = ArrayList<ArticleWithFeed>(cursor.getCount())
        while (cursor.moveToNext()) {
          val _item: ArticleWithFeed
          val _tmpArticle: Article
          val _tmpId: Long
          _tmpId = cursor.getLong(_cursorIndexOfId)
          val _tmpIsUnread: Boolean
          val _tmp: Int
          _tmp = cursor.getInt(_cursorIndexOfIsUnread)
          _tmpIsUnread = _tmp != 0
          val _tmpIsTransientUnread: Boolean
          val _tmp_1: Int
          _tmp_1 = cursor.getInt(_cursorIndexOfIsTransientUnread)
          _tmpIsTransientUnread = _tmp_1 != 0
          val _tmpIsStarred: Boolean
          val _tmp_2: Int
          _tmp_2 = cursor.getInt(_cursorIndexOfIsStarred)
          _tmpIsStarred = _tmp_2 != 0
          val _tmpIsPublished: Boolean
          val _tmp_3: Int
          _tmp_3 = cursor.getInt(_cursorIndexOfIsPublished)
          _tmpIsPublished = _tmp_3 != 0
          val _tmpScore: Int
          _tmpScore = cursor.getInt(_cursorIndexOfScore)
          val _tmpLastTimeUpdate: Long
          _tmpLastTimeUpdate = cursor.getLong(_cursorIndexOfLastTimeUpdate)
          val _tmpIsUpdated: Boolean
          val _tmp_4: Int
          _tmp_4 = cursor.getInt(_cursorIndexOfIsUpdated)
          _tmpIsUpdated = _tmp_4 != 0
          val _tmpLink: String
          _tmpLink = cursor.getString(_cursorIndexOfLink)
          val _tmpFeedId: Long
          _tmpFeedId = cursor.getLong(_cursorIndexOfFeedId)
          val _tmpFlavorImageUri: String
          _tmpFlavorImageUri = cursor.getString(_cursorIndexOfFlavorImageUri)
          val _tmpContentExcerpt: String
          _tmpContentExcerpt = cursor.getString(_cursorIndexOfContentExcerpt)
          val _tmpContentData: ArticleContentIndexed
          val _tmpTitle: String
          _tmpTitle = cursor.getString(_cursorIndexOfTitle)
          val _tmpTags: String
          _tmpTags = cursor.getString(_cursorIndexOfTags)
          val _tmpContent: String
          _tmpContent = cursor.getString(_cursorIndexOfContent)
          val _tmpAuthor: String
          _tmpAuthor = cursor.getString(_cursorIndexOfAuthor)
          _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
          _tmpArticle =
              Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
          val _tmpFeed: FeedWithFavIcon?
          val _tmpKey_1: Long
          _tmpKey_1 = cursor.getLong(_cursorIndexOfFeedId)
          _tmpFeed = _collectionFeed.get(_tmpKey_1)
          if (_tmpFeed == null) {
            error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
          }
          _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
          _result.add(_item)
        }
        return _result
      }
    }
  }

  public override suspend fun getMostUnreadTags(count: Int): List<String> {
    val _sql: String =
        "SELECT tag  FROM articles_tags  JOIN articles ON (articles_tags.article_id = articles._id) WHERE articles.unread=1 GROUP BY tag ORDER BY COUNT(article_id) DESC LIMIT ? "
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, count.toLong())
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<List<String>> {
      public override fun call(): List<String> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _result: MutableList<String> = ArrayList<String>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: String
            _item = _cursor.getString(0)
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

  public override suspend fun getUnreadArticlesForTag(tag: String, count: Int):
      List<ArticleWithFeed> {
    val _sql: String =
        "SELECT articles.* FROM articles  JOIN articles_tags ON (articles_tags.article_id = articles._id) WHERE articles_tags.tag=? AND unread=1 ORDER BY last_time_update DESC LIMIT ?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 2)
    var _argIndex: Int = 1
    _statement.bindString(_argIndex, tag)
    _argIndex = 2
    _statement.bindLong(_argIndex, count.toLong())
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, true, _cancellationSignal, object : Callable<List<ArticleWithFeed>> {
      public override fun call(): List<ArticleWithFeed> {
        __db.beginTransaction()
        try {
          val _cursor: Cursor = query(__db, _statement, true, null)
          try {
            val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
            val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(_cursor, "unread")
            val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(_cursor,
                "transiant_unread")
            val _cursorIndexOfIsStarred: Int = getColumnIndexOrThrow(_cursor, "marked")
            val _cursorIndexOfIsPublished: Int = getColumnIndexOrThrow(_cursor, "published")
            val _cursorIndexOfScore: Int = getColumnIndexOrThrow(_cursor, "score")
            val _cursorIndexOfLastTimeUpdate: Int = getColumnIndexOrThrow(_cursor,
                "last_time_update")
            val _cursorIndexOfIsUpdated: Int = getColumnIndexOrThrow(_cursor, "is_updated")
            val _cursorIndexOfLink: Int = getColumnIndexOrThrow(_cursor, "link")
            val _cursorIndexOfFeedId: Int = getColumnIndexOrThrow(_cursor, "feed_id")
            val _cursorIndexOfFlavorImageUri: Int = getColumnIndexOrThrow(_cursor,
                "flavor_image_uri")
            val _cursorIndexOfContentExcerpt: Int = getColumnIndexOrThrow(_cursor,
                "content_excerpt")
            val _cursorIndexOfTitle: Int = getColumnIndexOrThrow(_cursor, "title")
            val _cursorIndexOfTags: Int = getColumnIndexOrThrow(_cursor, "tags")
            val _cursorIndexOfContent: Int = getColumnIndexOrThrow(_cursor, "content")
            val _cursorIndexOfAuthor: Int = getColumnIndexOrThrow(_cursor, "author")
            val _collectionFeed: LongSparseArray<FeedWithFavIcon?> =
                LongSparseArray<FeedWithFavIcon?>()
            while (_cursor.moveToNext()) {
              val _tmpKey: Long
              _tmpKey = _cursor.getLong(_cursorIndexOfFeedId)
              _collectionFeed.put(_tmpKey, null)
            }
            _cursor.moveToPosition(-1)
            __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_collectionFeed)
            val _result: MutableList<ArticleWithFeed> =
                ArrayList<ArticleWithFeed>(_cursor.getCount())
            while (_cursor.moveToNext()) {
              val _item: ArticleWithFeed
              val _tmpArticle: Article
              val _tmpId: Long
              _tmpId = _cursor.getLong(_cursorIndexOfId)
              val _tmpIsUnread: Boolean
              val _tmp: Int
              _tmp = _cursor.getInt(_cursorIndexOfIsUnread)
              _tmpIsUnread = _tmp != 0
              val _tmpIsTransientUnread: Boolean
              val _tmp_1: Int
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsTransientUnread)
              _tmpIsTransientUnread = _tmp_1 != 0
              val _tmpIsStarred: Boolean
              val _tmp_2: Int
              _tmp_2 = _cursor.getInt(_cursorIndexOfIsStarred)
              _tmpIsStarred = _tmp_2 != 0
              val _tmpIsPublished: Boolean
              val _tmp_3: Int
              _tmp_3 = _cursor.getInt(_cursorIndexOfIsPublished)
              _tmpIsPublished = _tmp_3 != 0
              val _tmpScore: Int
              _tmpScore = _cursor.getInt(_cursorIndexOfScore)
              val _tmpLastTimeUpdate: Long
              _tmpLastTimeUpdate = _cursor.getLong(_cursorIndexOfLastTimeUpdate)
              val _tmpIsUpdated: Boolean
              val _tmp_4: Int
              _tmp_4 = _cursor.getInt(_cursorIndexOfIsUpdated)
              _tmpIsUpdated = _tmp_4 != 0
              val _tmpLink: String
              _tmpLink = _cursor.getString(_cursorIndexOfLink)
              val _tmpFeedId: Long
              _tmpFeedId = _cursor.getLong(_cursorIndexOfFeedId)
              val _tmpFlavorImageUri: String
              _tmpFlavorImageUri = _cursor.getString(_cursorIndexOfFlavorImageUri)
              val _tmpContentExcerpt: String
              _tmpContentExcerpt = _cursor.getString(_cursorIndexOfContentExcerpt)
              val _tmpContentData: ArticleContentIndexed
              val _tmpTitle: String
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle)
              val _tmpTags: String
              _tmpTags = _cursor.getString(_cursorIndexOfTags)
              val _tmpContent: String
              _tmpContent = _cursor.getString(_cursorIndexOfContent)
              val _tmpAuthor: String
              _tmpAuthor = _cursor.getString(_cursorIndexOfAuthor)
              _tmpContentData = ArticleContentIndexed(_tmpTitle,_tmpTags,_tmpContent,_tmpAuthor)
              _tmpArticle =
                  Article(_tmpId,_tmpContentData,_tmpIsUnread,_tmpIsTransientUnread,_tmpIsStarred,_tmpIsPublished,_tmpScore,_tmpLastTimeUpdate,_tmpIsUpdated,_tmpLink,_tmpFeedId,_tmpFlavorImageUri,_tmpContentExcerpt)
              val _tmpFeed: FeedWithFavIcon?
              val _tmpKey_1: Long
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfFeedId)
              _tmpFeed = _collectionFeed.get(_tmpKey_1)
              if (_tmpFeed == null) {
                error("Relationship item 'feed' was expected to be NON-NULL but is NULL in @Relation involving a parent column named 'feed_id' and entityColumn named '_id'.")
              }
              _item = ArticleWithFeed(_tmpArticle,_tmpFeed)
              _result.add(_item)
            }
            __db.setTransactionSuccessful()
            return _result
          } finally {
            _cursor.close()
            _statement.release()
          }
        } finally {
          __db.endTransaction()
        }
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

  private
      fun __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(_map: LongSparseArray<FeedWithFavIcon?>) {
    if (_map.isEmpty()) {
      return
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      recursiveFetchLongSparseArray(_map, false) {
        __fetchRelationshipfeedsAscomGeekorumTtrssDataFeedWithFavIcon(it)
      }
      return
    }
    val _stringBuilder: StringBuilder = newStringBuilder()
    _stringBuilder.append("SELECT `_id`,`url`,`title`,`cat_id`,`display_title`,`last_time_update`,`unread_count`,`is_subscribed` FROM `feeds` WHERE `_id` IN (")
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
    val _cursor: Cursor = query(__db, _stmt, true, null)
    try {
      val _itemKeyIndex: Int = getColumnIndex(_cursor, "_id")
      if (_itemKeyIndex == -1) {
        return
      }
      val _cursorIndexOfId: Int = 0
      val _cursorIndexOfUrl: Int = 1
      val _cursorIndexOfTitle: Int = 2
      val _cursorIndexOfCatId: Int = 3
      val _cursorIndexOfDisplayTitle: Int = 4
      val _cursorIndexOfLastTimeUpdate: Int = 5
      val _cursorIndexOfUnreadCount: Int = 6
      val _cursorIndexOfIsSubscribed: Int = 7
      val _collectionFavIcon: LongSparseArray<FeedFavIcon?> = LongSparseArray<FeedFavIcon?>()
      while (_cursor.moveToNext()) {
        val _tmpKey: Long
        _tmpKey = _cursor.getLong(_cursorIndexOfId)
        _collectionFavIcon.put(_tmpKey, null)
      }
      _cursor.moveToPosition(-1)
      __fetchRelationshipfeedFavIconAscomGeekorumTtrssDataFeedFavIcon(_collectionFavIcon)
      while (_cursor.moveToNext()) {
        val _tmpKey_1: Long
        _tmpKey_1 = _cursor.getLong(_itemKeyIndex)
        if (_map.containsKey(_tmpKey_1)) {
          val _item_1: FeedWithFavIcon
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
          val _tmpKey_2: Long
          _tmpKey_2 = _cursor.getLong(_cursorIndexOfId)
          _tmpFavIcon = _collectionFavIcon.get(_tmpKey_2)
          _item_1 = FeedWithFavIcon(_tmpFeed,_tmpFavIcon)
          _map.put(_tmpKey_1, _item_1)
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
