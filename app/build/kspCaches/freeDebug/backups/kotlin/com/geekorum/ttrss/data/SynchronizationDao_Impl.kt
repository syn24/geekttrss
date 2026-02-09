package com.geekorum.ttrss.`data`

import android.database.Cursor
import android.os.CancellationSignal
import androidx.room.CoroutinesRoom
import androidx.room.CoroutinesRoom.Companion.execute
import androidx.room.EntityDeletionOrUpdateAdapter
import androidx.room.EntityInsertionAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.RoomSQLiteQuery.Companion.acquire
import androidx.room.SharedSQLiteStatement
import androidx.room.util.createCancellationSignal
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.query
import androidx.room.withTransaction
import androidx.sqlite.db.SupportSQLiteStatement
import java.lang.Class
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class SynchronizationDao_Impl(
  __db: RoomDatabase,
) : SynchronizationDao() {
  private val __db: RoomDatabase

  private val __insertionAdapterOfCategory: EntityInsertionAdapter<Category>

  private val __insertionAdapterOfFeed: EntityInsertionAdapter<Feed>

  private val __insertionAdapterOfFeedFavIcon: EntityInsertionAdapter<FeedFavIcon>

  private val __insertionAdapterOfArticle: EntityInsertionAdapter<Article>

  private val __insertionAdapterOfArticlesTags: EntityInsertionAdapter<ArticlesTags>

  private val __insertionAdapterOfAttachment: EntityInsertionAdapter<Attachment>

  private val __deletionAdapterOfTransaction: EntityDeletionOrUpdateAdapter<Transaction>

  private val __deletionAdapterOfCategory: EntityDeletionOrUpdateAdapter<Category>

  private val __deletionAdapterOfFeed: EntityDeletionOrUpdateAdapter<Feed>

  private val __updateAdapterOfArticle: EntityDeletionOrUpdateAdapter<Article>

  private val __updateAdapterOfMetadataAsArticle: EntityDeletionOrUpdateAdapter<Metadata>

  private val __preparedStmtOfDeleteArticleFromFeed: SharedSQLiteStatement

  private val __preparedStmtOfDeleteFeedFavIcon: SharedSQLiteStatement
  init {
    this.__db = __db
    this.__insertionAdapterOfCategory = object : EntityInsertionAdapter<Category>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `categories` (`_id`,`title`,`unread_count`) VALUES (?,?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Category) {
        statement.bindLong(1, entity.id)
        statement.bindString(2, entity.title)
        statement.bindLong(3, entity.unreadCount.toLong())
      }
    }
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
    this.__insertionAdapterOfFeedFavIcon = object : EntityInsertionAdapter<FeedFavIcon>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `feed_fav_icon` (`_id`,`url`) VALUES (?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: FeedFavIcon) {
        statement.bindLong(1, entity.id)
        statement.bindString(2, entity.url)
      }
    }
    this.__insertionAdapterOfArticle = object : EntityInsertionAdapter<Article>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `articles` (`_id`,`unread`,`transiant_unread`,`marked`,`published`,`score`,`last_time_update`,`is_updated`,`link`,`feed_id`,`flavor_image_uri`,`content_excerpt`,`title`,`tags`,`content`,`author`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Article) {
        statement.bindLong(1, entity.id)
        val _tmp: Int = if (entity.isUnread) 1 else 0
        statement.bindLong(2, _tmp.toLong())
        val _tmp_1: Int = if (entity.isTransientUnread) 1 else 0
        statement.bindLong(3, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isStarred) 1 else 0
        statement.bindLong(4, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.isPublished) 1 else 0
        statement.bindLong(5, _tmp_3.toLong())
        statement.bindLong(6, entity.score.toLong())
        statement.bindLong(7, entity.lastTimeUpdate)
        val _tmp_4: Int = if (entity.isUpdated) 1 else 0
        statement.bindLong(8, _tmp_4.toLong())
        statement.bindString(9, entity.link)
        statement.bindLong(10, entity.feedId)
        statement.bindString(11, entity.flavorImageUri)
        statement.bindString(12, entity.contentExcerpt)
        val _tmpContentData: ArticleContentIndexed = entity.contentData
        statement.bindString(13, _tmpContentData.title)
        statement.bindString(14, _tmpContentData.tags)
        statement.bindString(15, _tmpContentData.content)
        statement.bindString(16, _tmpContentData.author)
      }
    }
    this.__insertionAdapterOfArticlesTags = object : EntityInsertionAdapter<ArticlesTags>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `articles_tags` (`article_id`,`tag`) VALUES (?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: ArticlesTags) {
        statement.bindLong(1, entity.articleId)
        statement.bindString(2, entity.tag)
      }
    }
    this.__insertionAdapterOfAttachment = object : EntityInsertionAdapter<Attachment>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `attachments` (`_id`,`post_id`,`content_url`,`content_type`,`title`,`duration`,`width`,`height`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Attachment) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.postId)
        statement.bindString(3, entity.contentUrl)
        statement.bindString(4, entity.contentType)
        statement.bindString(5, entity.title)
        statement.bindLong(6, entity.duration)
        statement.bindLong(7, entity.width.toLong())
        statement.bindLong(8, entity.height.toLong())
      }
    }
    this.__deletionAdapterOfTransaction = object : EntityDeletionOrUpdateAdapter<Transaction>(__db)
        {
      protected override fun createQuery(): String = "DELETE FROM `transactions` WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Transaction) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__deletionAdapterOfCategory = object : EntityDeletionOrUpdateAdapter<Category>(__db) {
      protected override fun createQuery(): String = "DELETE FROM `categories` WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Category) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__deletionAdapterOfFeed = object : EntityDeletionOrUpdateAdapter<Feed>(__db) {
      protected override fun createQuery(): String = "DELETE FROM `feeds` WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Feed) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfArticle = object : EntityDeletionOrUpdateAdapter<Article>(__db) {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `articles` SET `_id` = ?,`unread` = ?,`transiant_unread` = ?,`marked` = ?,`published` = ?,`score` = ?,`last_time_update` = ?,`is_updated` = ?,`link` = ?,`feed_id` = ?,`flavor_image_uri` = ?,`content_excerpt` = ?,`title` = ?,`tags` = ?,`content` = ?,`author` = ? WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Article) {
        statement.bindLong(1, entity.id)
        val _tmp: Int = if (entity.isUnread) 1 else 0
        statement.bindLong(2, _tmp.toLong())
        val _tmp_1: Int = if (entity.isTransientUnread) 1 else 0
        statement.bindLong(3, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isStarred) 1 else 0
        statement.bindLong(4, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.isPublished) 1 else 0
        statement.bindLong(5, _tmp_3.toLong())
        statement.bindLong(6, entity.score.toLong())
        statement.bindLong(7, entity.lastTimeUpdate)
        val _tmp_4: Int = if (entity.isUpdated) 1 else 0
        statement.bindLong(8, _tmp_4.toLong())
        statement.bindString(9, entity.link)
        statement.bindLong(10, entity.feedId)
        statement.bindString(11, entity.flavorImageUri)
        statement.bindString(12, entity.contentExcerpt)
        val _tmpContentData: ArticleContentIndexed = entity.contentData
        statement.bindString(13, _tmpContentData.title)
        statement.bindString(14, _tmpContentData.tags)
        statement.bindString(15, _tmpContentData.content)
        statement.bindString(16, _tmpContentData.author)
        statement.bindLong(17, entity.id)
      }
    }
    this.__updateAdapterOfMetadataAsArticle = object : EntityDeletionOrUpdateAdapter<Metadata>(__db)
        {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `articles` SET `_id` = ?,`unread` = ?,`transiant_unread` = ?,`marked` = ?,`published` = ?,`last_time_update` = ?,`is_updated` = ? WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Metadata) {
        statement.bindLong(1, entity.id)
        val _tmp: Int = if (entity.isUnread) 1 else 0
        statement.bindLong(2, _tmp.toLong())
        val _tmp_1: Int = if (entity.isTransientUnread) 1 else 0
        statement.bindLong(3, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isStarred) 1 else 0
        statement.bindLong(4, _tmp_2.toLong())
        val _tmp_3: Int = if (entity.isPublished) 1 else 0
        statement.bindLong(5, _tmp_3.toLong())
        statement.bindLong(6, entity.lastTimeUpdated)
        val _tmp_4: Int = if (entity.isUpdated) 1 else 0
        statement.bindLong(7, _tmp_4.toLong())
        statement.bindLong(8, entity.id)
      }
    }
    this.__preparedStmtOfDeleteArticleFromFeed = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "DELETE FROM ARTICLES where feed_id=?"
        return _query
      }
    }
    this.__preparedStmtOfDeleteFeedFavIcon = object : SharedSQLiteStatement(__db) {
      public override fun createQuery(): String {
        val _query: String = "DELETE FROM feed_fav_icon where _id=?"
        return _query
      }
    }
  }

  public override suspend fun insertCategories(categories: List<Category>): Unit =
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

  public override suspend fun insertFeeds(feeds: List<Feed>): Unit = CoroutinesRoom.execute(__db,
      true, object : Callable<Unit> {
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

  public override suspend fun setFeedFavIcon(feedFavIcon: FeedFavIcon): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfFeedFavIcon.insert(feedFavIcon)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun insertArticles(dataArticles: List<Article>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfArticle.insert(dataArticles)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun insertArticlesTags(articlesTags: List<ArticlesTags>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfArticlesTags.insert(articlesTags)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun insertAttachments(attachments: List<Attachment>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfAttachment.insert(attachments)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun deleteTransaction(transaction: Transaction): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __deletionAdapterOfTransaction.handle(transaction)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun deleteCategories(categories: List<Category>): Unit =
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

  internal override suspend fun deleteFeeds(toBeDelete: List<Feed>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __deletionAdapterOfFeed.handleMultiple(toBeDelete)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun updateArticle(article: Article): Unit = CoroutinesRoom.execute(__db,
      true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __updateAdapterOfArticle.handle(article)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun updateArticlesMetadata(metadata: List<Metadata>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __updateAdapterOfMetadataAsArticle.handleMultiple(metadata)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun deleteFeedsAndRelatedData(toBeDelete: List<Feed>) {
    __db.withTransaction {
      super@SynchronizationDao_Impl.deleteFeedsAndRelatedData(toBeDelete)
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

  internal override suspend fun deleteFeedFavIcon(feedId: Long): Unit = CoroutinesRoom.execute(__db,
      true, object : Callable<Unit> {
    public override fun call() {
      val _stmt: SupportSQLiteStatement = __preparedStmtOfDeleteFeedFavIcon.acquire()
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
        __preparedStmtOfDeleteFeedFavIcon.release(_stmt)
      }
    }
  })

  public override suspend fun getAllTransactions(): List<Transaction> {
    val _sql: String = "SELECT * FROM transactions"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<List<Transaction>> {
      public override fun call(): List<Transaction> {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
          val _cursorIndexOfArticleId: Int = getColumnIndexOrThrow(_cursor, "article_id")
          val _cursorIndexOfField: Int = getColumnIndexOrThrow(_cursor, "field")
          val _cursorIndexOfValue: Int = getColumnIndexOrThrow(_cursor, "value")
          val _result: MutableList<Transaction> = ArrayList<Transaction>(_cursor.getCount())
          while (_cursor.moveToNext()) {
            val _item: Transaction
            val _tmpId: Long
            _tmpId = _cursor.getLong(_cursorIndexOfId)
            val _tmpArticleId: Long
            _tmpArticleId = _cursor.getLong(_cursorIndexOfArticleId)
            val _tmpField: String
            _tmpField = _cursor.getString(_cursorIndexOfField)
            val _tmpValue: Boolean
            val _tmp: Int
            _tmp = _cursor.getInt(_cursorIndexOfValue)
            _tmpValue = _tmp != 0
            _item = Transaction(_tmpId,_tmpArticleId,_tmpField,_tmpValue)
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

  public override suspend fun getLatestArticleId(): Long? {
    val _sql: String = "SELECT _id FROM articles ORDER BY _id DESC LIMIT 1"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<Long?> {
      public override fun call(): Long? {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _result: Long?
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null
            } else {
              _result = _cursor.getLong(0)
            }
          } else {
            _result = null
          }
          return _result
        } finally {
          _cursor.close()
          _statement.release()
        }
      }
    })
  }

  public override suspend fun getLatestArticleIdFromFeed(feedId: Long): Long? {
    val _sql: String = "SELECT _id FROM articles WHERE feed_id=? ORDER BY _id DESC LIMIT 1"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, feedId)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<Long?> {
      public override fun call(): Long? {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _result: Long?
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null
            } else {
              _result = _cursor.getLong(0)
            }
          } else {
            _result = null
          }
          return _result
        } finally {
          _cursor.close()
          _statement.release()
        }
      }
    })
  }

  public override suspend fun getAllCategories(): List<Category> {
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

  public override suspend fun getAllFeeds(): List<Feed> {
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

  public override fun getAllFeedFavIcons(): List<FeedFavIcon> {
    val _sql: String = "SELECT * FROM feed_fav_icon"
    val _statement: RoomSQLiteQuery = acquire(_sql, 0)
    __db.assertNotSuspendingTransaction()
    val _cursor: Cursor = query(__db, _statement, false, null)
    try {
      val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
      val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "url")
      val _result: MutableList<FeedFavIcon> = ArrayList<FeedFavIcon>(_cursor.getCount())
      while (_cursor.moveToNext()) {
        val _item: FeedFavIcon
        val _tmpId: Long
        _tmpId = _cursor.getLong(_cursorIndexOfId)
        val _tmpUrl: String
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
        _item = FeedFavIcon(_tmpId,_tmpUrl)
        _result.add(_item)
      }
      return _result
    } finally {
      _cursor.close()
      _statement.release()
    }
  }

  public override suspend fun getArticleById(id: Long): Article? {
    val _sql: String = "SELECT * FROM articles WHERE _id=?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, id)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<Article?> {
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
          _statement.release()
        }
      }
    })
  }

  public override fun getLatestArticleFromFeed(feedId: Long): Article? {
    val _sql: String = "SELECT * FROM articles WHERE feed_id=? ORDER BY feed_id DESC LIMIT 1"
    val _statement: RoomSQLiteQuery = acquire(_sql, 1)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, feedId)
    __db.assertNotSuspendingTransaction()
    val _cursor: Cursor = query(__db, _statement, false, null)
    try {
      val _cursorIndexOfId: Int = getColumnIndexOrThrow(_cursor, "_id")
      val _cursorIndexOfIsUnread: Int = getColumnIndexOrThrow(_cursor, "unread")
      val _cursorIndexOfIsTransientUnread: Int = getColumnIndexOrThrow(_cursor, "transiant_unread")
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
      _statement.release()
    }
  }

  public companion object {
    @JvmStatic
    public fun getRequiredConverters(): List<Class<*>> = emptyList()
  }
}
