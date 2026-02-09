package com.geekorum.ttrss.`data`

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.room.RoomOpenHelper
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.FtsTableInfo
import androidx.room.util.TableInfo
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.geekorum.ttrss.providers.PurgeArticlesDao
import com.geekorum.ttrss.providers.PurgeArticlesDao_Impl
import java.lang.Class
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import javax.`annotation`.processing.Generated
import kotlin.Any
import kotlin.Boolean
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.Set
import androidx.room.util.FtsTableInfo.Companion.read as ftsTableInfoRead
import androidx.room.util.TableInfo.Companion.read as tableInfoRead

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class ArticlesDatabase_Impl : ArticlesDatabase() {
  private val _articleDao: Lazy<ArticleDao> = lazy {
    ArticleDao_Impl(this)
  }


  private val _accountInfoDao: Lazy<AccountInfoDao> = lazy {
    AccountInfoDao_Impl(this)
  }


  private val _transactionsDao: Lazy<TransactionsDao> = lazy {
    TransactionsDao_Impl(this)
  }


  private val _synchronizationDao: Lazy<SynchronizationDao> = lazy {
    SynchronizationDao_Impl(this)
  }


  private val _purgeArticlesDao: Lazy<PurgeArticlesDao> = lazy {
    PurgeArticlesDao_Impl(this)
  }


  private val _feedsDao: Lazy<FeedsDao> = lazy {
    FeedsDao_Impl(this)
  }


  private val _manageFeedsDao: Lazy<ManageFeedsDao> = lazy {
    ManageFeedsDao_Impl(this)
  }


  protected override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
    val _openCallback: SupportSQLiteOpenHelper.Callback = RoomOpenHelper(config, object :
        RoomOpenHelper.Delegate(15) {
      public override fun createAllTables(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `articles` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `unread` INTEGER NOT NULL, `transiant_unread` INTEGER NOT NULL, `marked` INTEGER NOT NULL, `published` INTEGER NOT NULL, `score` INTEGER NOT NULL, `last_time_update` INTEGER NOT NULL, `is_updated` INTEGER NOT NULL, `link` TEXT NOT NULL, `feed_id` INTEGER NOT NULL, `flavor_image_uri` TEXT NOT NULL, `content_excerpt` TEXT NOT NULL, `title` TEXT NOT NULL, `tags` TEXT NOT NULL, `content` TEXT NOT NULL, `author` TEXT NOT NULL, FOREIGN KEY(`feed_id`) REFERENCES `feeds`(`_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_unread_last_time_update` ON `articles` (`unread`, `last_time_update`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_feed_id_unread_last_time_update` ON `articles` (`feed_id`, `unread`, `last_time_update`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_marked_unread_last_time_update` ON `articles` (`marked`, `unread`, `last_time_update`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_feed_id` ON `articles` (`feed_id`)")
        db.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `ArticleFTS` USING FTS4(`title` TEXT NOT NULL, `tags` TEXT NOT NULL, `content` TEXT NOT NULL, `author` TEXT NOT NULL, content=`articles`)")
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_BEFORE_UPDATE BEFORE UPDATE ON `articles` BEGIN DELETE FROM `ArticleFTS` WHERE `docid`=OLD.`rowid`; END")
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_BEFORE_DELETE BEFORE DELETE ON `articles` BEGIN DELETE FROM `ArticleFTS` WHERE `docid`=OLD.`rowid`; END")
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_AFTER_UPDATE AFTER UPDATE ON `articles` BEGIN INSERT INTO `ArticleFTS`(`docid`, `title`, `tags`, `content`, `author`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`tags`, NEW.`content`, NEW.`author`); END")
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_AFTER_INSERT AFTER INSERT ON `articles` BEGIN INSERT INTO `ArticleFTS`(`docid`, `title`, `tags`, `content`, `author`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`tags`, NEW.`content`, NEW.`author`); END")
        db.execSQL("CREATE TABLE IF NOT EXISTS `articles_tags` (`article_id` INTEGER NOT NULL, `tag` TEXT NOT NULL, PRIMARY KEY(`tag`, `article_id`), FOREIGN KEY(`article_id`) REFERENCES `articles`(`_id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_tags_article_id` ON `articles_tags` (`article_id`)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_tags_tag` ON `articles_tags` (`tag`)")
        db.execSQL("CREATE TABLE IF NOT EXISTS `attachments` (`_id` INTEGER NOT NULL, `post_id` INTEGER NOT NULL, `content_url` TEXT NOT NULL, `content_type` TEXT NOT NULL, `title` TEXT NOT NULL, `duration` INTEGER NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL, PRIMARY KEY(`_id`), FOREIGN KEY(`post_id`) REFERENCES `articles`(`_id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_attachments_post_id` ON `attachments` (`post_id`)")
        db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`_id` INTEGER NOT NULL, `title` TEXT NOT NULL, `unread_count` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
        db.execSQL("CREATE TABLE IF NOT EXISTS `feeds` (`_id` INTEGER NOT NULL, `url` TEXT NOT NULL, `title` TEXT NOT NULL, `cat_id` INTEGER NOT NULL, `display_title` TEXT NOT NULL, `last_time_update` INTEGER NOT NULL, `unread_count` INTEGER NOT NULL, `is_subscribed` INTEGER NOT NULL, PRIMARY KEY(`_id`), FOREIGN KEY(`cat_id`) REFERENCES `categories`(`_id`) ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED)")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_feeds_cat_id` ON `feeds` (`cat_id`)")
        db.execSQL("CREATE TABLE IF NOT EXISTS `feed_fav_icon` (`_id` INTEGER NOT NULL, `url` TEXT NOT NULL, PRIMARY KEY(`_id`), FOREIGN KEY(`_id`) REFERENCES `feeds`(`_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
        db.execSQL("CREATE TABLE IF NOT EXISTS `transactions` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `article_id` INTEGER NOT NULL, `field` TEXT NOT NULL, `value` INTEGER NOT NULL, FOREIGN KEY(`article_id`) REFERENCES `articles`(`_id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_transactions_article_id` ON `transactions` (`article_id`)")
        db.execSQL("CREATE TABLE IF NOT EXISTS `account_info` (`server_version` TEXT NOT NULL, `api_level` INTEGER NOT NULL, `account_username` TEXT NOT NULL, `account_url` TEXT NOT NULL, PRIMARY KEY(`account_username`, `account_url`))")
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '682abdc1de00ba7df9d52d44c0c9f759')")
      }

      public override fun dropAllTables(db: SupportSQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS `articles`")
        db.execSQL("DROP TABLE IF EXISTS `ArticleFTS`")
        db.execSQL("DROP TABLE IF EXISTS `articles_tags`")
        db.execSQL("DROP TABLE IF EXISTS `attachments`")
        db.execSQL("DROP TABLE IF EXISTS `categories`")
        db.execSQL("DROP TABLE IF EXISTS `feeds`")
        db.execSQL("DROP TABLE IF EXISTS `feed_fav_icon`")
        db.execSQL("DROP TABLE IF EXISTS `transactions`")
        db.execSQL("DROP TABLE IF EXISTS `account_info`")
        val _callbacks: List<RoomDatabase.Callback>? = mCallbacks
        if (_callbacks != null) {
          for (_callback: RoomDatabase.Callback in _callbacks) {
            _callback.onDestructiveMigration(db)
          }
        }
      }

      public override fun onCreate(db: SupportSQLiteDatabase) {
        val _callbacks: List<RoomDatabase.Callback>? = mCallbacks
        if (_callbacks != null) {
          for (_callback: RoomDatabase.Callback in _callbacks) {
            _callback.onCreate(db)
          }
        }
      }

      public override fun onOpen(db: SupportSQLiteDatabase) {
        mDatabase = db
        db.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(db)
        val _callbacks: List<RoomDatabase.Callback>? = mCallbacks
        if (_callbacks != null) {
          for (_callback: RoomDatabase.Callback in _callbacks) {
            _callback.onOpen(db)
          }
        }
      }

      public override fun onPreMigrate(db: SupportSQLiteDatabase) {
        dropFtsSyncTriggers(db)
      }

      public override fun onPostMigrate(db: SupportSQLiteDatabase) {
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_BEFORE_UPDATE BEFORE UPDATE ON `articles` BEGIN DELETE FROM `ArticleFTS` WHERE `docid`=OLD.`rowid`; END")
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_BEFORE_DELETE BEFORE DELETE ON `articles` BEGIN DELETE FROM `ArticleFTS` WHERE `docid`=OLD.`rowid`; END")
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_AFTER_UPDATE AFTER UPDATE ON `articles` BEGIN INSERT INTO `ArticleFTS`(`docid`, `title`, `tags`, `content`, `author`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`tags`, NEW.`content`, NEW.`author`); END")
        db.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_ArticleFTS_AFTER_INSERT AFTER INSERT ON `articles` BEGIN INSERT INTO `ArticleFTS`(`docid`, `title`, `tags`, `content`, `author`) VALUES (NEW.`rowid`, NEW.`title`, NEW.`tags`, NEW.`content`, NEW.`author`); END")
      }

      public override fun onValidateSchema(db: SupportSQLiteDatabase):
          RoomOpenHelper.ValidationResult {
        val _columnsArticles: HashMap<String, TableInfo.Column> =
            HashMap<String, TableInfo.Column>(16)
        _columnsArticles.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("unread", TableInfo.Column("unread", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("transiant_unread", TableInfo.Column("transiant_unread", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("marked", TableInfo.Column("marked", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("published", TableInfo.Column("published", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("score", TableInfo.Column("score", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("last_time_update", TableInfo.Column("last_time_update", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("is_updated", TableInfo.Column("is_updated", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("link", TableInfo.Column("link", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("feed_id", TableInfo.Column("feed_id", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("flavor_image_uri", TableInfo.Column("flavor_image_uri", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("content_excerpt", TableInfo.Column("content_excerpt", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("tags", TableInfo.Column("tags", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("content", TableInfo.Column("content", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArticles.put("author", TableInfo.Column("author", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysArticles: HashSet<TableInfo.ForeignKey> = HashSet<TableInfo.ForeignKey>(1)
        _foreignKeysArticles.add(TableInfo.ForeignKey("feeds", "NO ACTION", "NO ACTION",
            listOf("feed_id"), listOf("_id")))
        val _indicesArticles: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(4)
        _indicesArticles.add(TableInfo.Index("index_articles_unread_last_time_update", false,
            listOf("unread", "last_time_update"), listOf("ASC", "ASC")))
        _indicesArticles.add(TableInfo.Index("index_articles_feed_id_unread_last_time_update",
            false, listOf("feed_id", "unread", "last_time_update"), listOf("ASC", "ASC", "ASC")))
        _indicesArticles.add(TableInfo.Index("index_articles_marked_unread_last_time_update", false,
            listOf("marked", "unread", "last_time_update"), listOf("ASC", "ASC", "ASC")))
        _indicesArticles.add(TableInfo.Index("index_articles_feed_id", false, listOf("feed_id"),
            listOf("ASC")))
        val _infoArticles: TableInfo = TableInfo("articles", _columnsArticles, _foreignKeysArticles,
            _indicesArticles)
        val _existingArticles: TableInfo = tableInfoRead(db, "articles")
        if (!_infoArticles.equals(_existingArticles)) {
          return RoomOpenHelper.ValidationResult(false, """
              |articles(com.geekorum.ttrss.data.Article).
              | Expected:
              |""".trimMargin() + _infoArticles + """
              |
              | Found:
              |""".trimMargin() + _existingArticles)
        }
        val _columnsArticleFTS: HashSet<String> = HashSet<String>(4)
        _columnsArticleFTS.add("title")
        _columnsArticleFTS.add("tags")
        _columnsArticleFTS.add("content")
        _columnsArticleFTS.add("author")
        val _infoArticleFTS: FtsTableInfo = FtsTableInfo("ArticleFTS", _columnsArticleFTS,
            "CREATE VIRTUAL TABLE IF NOT EXISTS `ArticleFTS` USING FTS4(`title` TEXT NOT NULL, `tags` TEXT NOT NULL, `content` TEXT NOT NULL, `author` TEXT NOT NULL, content=`articles`)")
        val _existingArticleFTS: FtsTableInfo = ftsTableInfoRead(db, "ArticleFTS")
        if (!_infoArticleFTS.equals(_existingArticleFTS)) {
          return RoomOpenHelper.ValidationResult(false, """
              |ArticleFTS(com.geekorum.ttrss.data.ArticleFTS).
              | Expected:
              |""".trimMargin() + _infoArticleFTS + """
              |
              | Found:
              |""".trimMargin() + _existingArticleFTS)
        }
        val _columnsArticlesTags: HashMap<String, TableInfo.Column> =
            HashMap<String, TableInfo.Column>(2)
        _columnsArticlesTags.put("article_id", TableInfo.Column("article_id", "INTEGER", true, 2,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArticlesTags.put("tag", TableInfo.Column("tag", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysArticlesTags: HashSet<TableInfo.ForeignKey> =
            HashSet<TableInfo.ForeignKey>(1)
        _foreignKeysArticlesTags.add(TableInfo.ForeignKey("articles", "CASCADE", "NO ACTION",
            listOf("article_id"), listOf("_id")))
        val _indicesArticlesTags: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(2)
        _indicesArticlesTags.add(TableInfo.Index("index_articles_tags_article_id", false,
            listOf("article_id"), listOf("ASC")))
        _indicesArticlesTags.add(TableInfo.Index("index_articles_tags_tag", false, listOf("tag"),
            listOf("ASC")))
        val _infoArticlesTags: TableInfo = TableInfo("articles_tags", _columnsArticlesTags,
            _foreignKeysArticlesTags, _indicesArticlesTags)
        val _existingArticlesTags: TableInfo = tableInfoRead(db, "articles_tags")
        if (!_infoArticlesTags.equals(_existingArticlesTags)) {
          return RoomOpenHelper.ValidationResult(false, """
              |articles_tags(com.geekorum.ttrss.data.ArticlesTags).
              | Expected:
              |""".trimMargin() + _infoArticlesTags + """
              |
              | Found:
              |""".trimMargin() + _existingArticlesTags)
        }
        val _columnsAttachments: HashMap<String, TableInfo.Column> =
            HashMap<String, TableInfo.Column>(8)
        _columnsAttachments.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttachments.put("post_id", TableInfo.Column("post_id", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttachments.put("content_url", TableInfo.Column("content_url", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAttachments.put("content_type", TableInfo.Column("content_type", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAttachments.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttachments.put("duration", TableInfo.Column("duration", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttachments.put("width", TableInfo.Column("width", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAttachments.put("height", TableInfo.Column("height", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAttachments: HashSet<TableInfo.ForeignKey> =
            HashSet<TableInfo.ForeignKey>(1)
        _foreignKeysAttachments.add(TableInfo.ForeignKey("articles", "CASCADE", "NO ACTION",
            listOf("post_id"), listOf("_id")))
        val _indicesAttachments: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(1)
        _indicesAttachments.add(TableInfo.Index("index_attachments_post_id", false,
            listOf("post_id"), listOf("ASC")))
        val _infoAttachments: TableInfo = TableInfo("attachments", _columnsAttachments,
            _foreignKeysAttachments, _indicesAttachments)
        val _existingAttachments: TableInfo = tableInfoRead(db, "attachments")
        if (!_infoAttachments.equals(_existingAttachments)) {
          return RoomOpenHelper.ValidationResult(false, """
              |attachments(com.geekorum.ttrss.data.Attachment).
              | Expected:
              |""".trimMargin() + _infoAttachments + """
              |
              | Found:
              |""".trimMargin() + _existingAttachments)
        }
        val _columnsCategories: HashMap<String, TableInfo.Column> =
            HashMap<String, TableInfo.Column>(3)
        _columnsCategories.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("unread_count", TableInfo.Column("unread_count", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCategories: HashSet<TableInfo.ForeignKey> = HashSet<TableInfo.ForeignKey>(0)
        val _indicesCategories: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(0)
        val _infoCategories: TableInfo = TableInfo("categories", _columnsCategories,
            _foreignKeysCategories, _indicesCategories)
        val _existingCategories: TableInfo = tableInfoRead(db, "categories")
        if (!_infoCategories.equals(_existingCategories)) {
          return RoomOpenHelper.ValidationResult(false, """
              |categories(com.geekorum.ttrss.data.Category).
              | Expected:
              |""".trimMargin() + _infoCategories + """
              |
              | Found:
              |""".trimMargin() + _existingCategories)
        }
        val _columnsFeeds: HashMap<String, TableInfo.Column> = HashMap<String, TableInfo.Column>(8)
        _columnsFeeds.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeds.put("url", TableInfo.Column("url", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeds.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeds.put("cat_id", TableInfo.Column("cat_id", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeds.put("display_title", TableInfo.Column("display_title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeds.put("last_time_update", TableInfo.Column("last_time_update", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeds.put("unread_count", TableInfo.Column("unread_count", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeeds.put("is_subscribed", TableInfo.Column("is_subscribed", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFeeds: HashSet<TableInfo.ForeignKey> = HashSet<TableInfo.ForeignKey>(1)
        _foreignKeysFeeds.add(TableInfo.ForeignKey("categories", "NO ACTION", "NO ACTION",
            listOf("cat_id"), listOf("_id")))
        val _indicesFeeds: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(1)
        _indicesFeeds.add(TableInfo.Index("index_feeds_cat_id", false, listOf("cat_id"),
            listOf("ASC")))
        val _infoFeeds: TableInfo = TableInfo("feeds", _columnsFeeds, _foreignKeysFeeds,
            _indicesFeeds)
        val _existingFeeds: TableInfo = tableInfoRead(db, "feeds")
        if (!_infoFeeds.equals(_existingFeeds)) {
          return RoomOpenHelper.ValidationResult(false, """
              |feeds(com.geekorum.ttrss.data.Feed).
              | Expected:
              |""".trimMargin() + _infoFeeds + """
              |
              | Found:
              |""".trimMargin() + _existingFeeds)
        }
        val _columnsFeedFavIcon: HashMap<String, TableInfo.Column> =
            HashMap<String, TableInfo.Column>(2)
        _columnsFeedFavIcon.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsFeedFavIcon.put("url", TableInfo.Column("url", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFeedFavIcon: HashSet<TableInfo.ForeignKey> =
            HashSet<TableInfo.ForeignKey>(1)
        _foreignKeysFeedFavIcon.add(TableInfo.ForeignKey("feeds", "NO ACTION", "NO ACTION",
            listOf("_id"), listOf("_id")))
        val _indicesFeedFavIcon: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(0)
        val _infoFeedFavIcon: TableInfo = TableInfo("feed_fav_icon", _columnsFeedFavIcon,
            _foreignKeysFeedFavIcon, _indicesFeedFavIcon)
        val _existingFeedFavIcon: TableInfo = tableInfoRead(db, "feed_fav_icon")
        if (!_infoFeedFavIcon.equals(_existingFeedFavIcon)) {
          return RoomOpenHelper.ValidationResult(false, """
              |feed_fav_icon(com.geekorum.ttrss.data.FeedFavIcon).
              | Expected:
              |""".trimMargin() + _infoFeedFavIcon + """
              |
              | Found:
              |""".trimMargin() + _existingFeedFavIcon)
        }
        val _columnsTransactions: HashMap<String, TableInfo.Column> =
            HashMap<String, TableInfo.Column>(4)
        _columnsTransactions.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTransactions.put("article_id", TableInfo.Column("article_id", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTransactions.put("field", TableInfo.Column("field", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsTransactions.put("value", TableInfo.Column("value", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTransactions: HashSet<TableInfo.ForeignKey> =
            HashSet<TableInfo.ForeignKey>(1)
        _foreignKeysTransactions.add(TableInfo.ForeignKey("articles", "CASCADE", "NO ACTION",
            listOf("article_id"), listOf("_id")))
        val _indicesTransactions: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(1)
        _indicesTransactions.add(TableInfo.Index("index_transactions_article_id", false,
            listOf("article_id"), listOf("ASC")))
        val _infoTransactions: TableInfo = TableInfo("transactions", _columnsTransactions,
            _foreignKeysTransactions, _indicesTransactions)
        val _existingTransactions: TableInfo = tableInfoRead(db, "transactions")
        if (!_infoTransactions.equals(_existingTransactions)) {
          return RoomOpenHelper.ValidationResult(false, """
              |transactions(com.geekorum.ttrss.data.Transaction).
              | Expected:
              |""".trimMargin() + _infoTransactions + """
              |
              | Found:
              |""".trimMargin() + _existingTransactions)
        }
        val _columnsAccountInfo: HashMap<String, TableInfo.Column> =
            HashMap<String, TableInfo.Column>(4)
        _columnsAccountInfo.put("server_version", TableInfo.Column("server_version", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountInfo.put("api_level", TableInfo.Column("api_level", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountInfo.put("account_username", TableInfo.Column("account_username", "TEXT",
            true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAccountInfo.put("account_url", TableInfo.Column("account_url", "TEXT", true, 2,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAccountInfo: HashSet<TableInfo.ForeignKey> =
            HashSet<TableInfo.ForeignKey>(0)
        val _indicesAccountInfo: HashSet<TableInfo.Index> = HashSet<TableInfo.Index>(0)
        val _infoAccountInfo: TableInfo = TableInfo("account_info", _columnsAccountInfo,
            _foreignKeysAccountInfo, _indicesAccountInfo)
        val _existingAccountInfo: TableInfo = tableInfoRead(db, "account_info")
        if (!_infoAccountInfo.equals(_existingAccountInfo)) {
          return RoomOpenHelper.ValidationResult(false, """
              |account_info(com.geekorum.ttrss.data.AccountInfo).
              | Expected:
              |""".trimMargin() + _infoAccountInfo + """
              |
              | Found:
              |""".trimMargin() + _existingAccountInfo)
        }
        return RoomOpenHelper.ValidationResult(true, null)
      }
    }, "682abdc1de00ba7df9d52d44c0c9f759", "5abf4326df65906ead733bec239ebe5b")
    val _sqliteConfig: SupportSQLiteOpenHelper.Configuration =
        SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build()
    val _helper: SupportSQLiteOpenHelper = config.sqliteOpenHelperFactory.create(_sqliteConfig)
    return _helper
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: HashMap<String, String> = HashMap<String, String>(1)
    _shadowTablesMap.put("ArticleFTS", "articles")
    val _viewTables: HashMap<String, Set<String>> = HashMap<String, Set<String>>(0)
    return InvalidationTracker(this, _shadowTablesMap, _viewTables,
        "articles","ArticleFTS","articles_tags","attachments","categories","feeds","feed_fav_icon","transactions","account_info")
  }

  public override fun clearAllTables() {
    super.assertNotMainThread()
    val _db: SupportSQLiteDatabase = super.openHelper.writableDatabase
    val _supportsDeferForeignKeys: Boolean = android.os.Build.VERSION.SDK_INT >=
        android.os.Build.VERSION_CODES.LOLLIPOP
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE")
      }
      super.beginTransaction()
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE")
      }
      _db.execSQL("DELETE FROM `articles`")
      _db.execSQL("DELETE FROM `ArticleFTS`")
      _db.execSQL("DELETE FROM `articles_tags`")
      _db.execSQL("DELETE FROM `attachments`")
      _db.execSQL("DELETE FROM `categories`")
      _db.execSQL("DELETE FROM `feed_fav_icon`")
      _db.execSQL("DELETE FROM `feeds`")
      _db.execSQL("DELETE FROM `transactions`")
      _db.execSQL("DELETE FROM `account_info`")
      super.setTransactionSuccessful()
    } finally {
      super.endTransaction()
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE")
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close()
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM")
      }
    }
  }

  protected override fun getRequiredTypeConverters(): Map<Class<out Any>, List<Class<out Any>>> {
    val _typeConvertersMap: HashMap<Class<out Any>, List<Class<out Any>>> =
        HashMap<Class<out Any>, List<Class<out Any>>>()
    _typeConvertersMap.put(ArticleDao::class.java, ArticleDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AccountInfoDao::class.java, AccountInfoDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(TransactionsDao::class.java,
        TransactionsDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SynchronizationDao::class.java,
        SynchronizationDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PurgeArticlesDao::class.java,
        PurgeArticlesDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FeedsDao::class.java, FeedsDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ManageFeedsDao::class.java, ManageFeedsDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecs(): Set<Class<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: HashSet<Class<out AutoMigrationSpec>> =
        HashSet<Class<out AutoMigrationSpec>>()
    return _autoMigrationSpecsSet
  }

  public override
      fun getAutoMigrations(autoMigrationSpecs: Map<Class<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = ArrayList<Migration>()
    _autoMigrations.add(ArticlesDatabase_AutoMigration_13_14_Impl())
    _autoMigrations.add(ArticlesDatabase_AutoMigration_14_15_Impl())
    return _autoMigrations
  }

  public override fun articleDao(): ArticleDao = _articleDao.value

  public override fun accountInfoDao(): AccountInfoDao = _accountInfoDao.value

  public override fun transactionsDao(): TransactionsDao = _transactionsDao.value

  public override fun synchronizationDao(): SynchronizationDao = _synchronizationDao.value

  public override fun articlesProvidersDao(): PurgeArticlesDao = _purgeArticlesDao.value

  public override fun feedsDao(): FeedsDao = _feedsDao.value

  public override fun manageFeedsDao(): ManageFeedsDao = _manageFeedsDao.value
}
