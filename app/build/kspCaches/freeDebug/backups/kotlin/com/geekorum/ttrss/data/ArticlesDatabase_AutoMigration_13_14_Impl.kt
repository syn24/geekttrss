package com.geekorum.ttrss.`data`

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.foreignKeyCheck
import androidx.sqlite.db.SupportSQLiteDatabase
import com.geekorum.ttrss.`data`.migrations.MigrationFrom13To14
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
internal class ArticlesDatabase_AutoMigration_13_14_Impl : Migration {
  private val callback: AutoMigrationSpec = MigrationFrom13To14()

  public constructor() : super(13, 14)

  public override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("CREATE TABLE IF NOT EXISTS `feed_fav_icon` (`_id` INTEGER NOT NULL, `url` TEXT NOT NULL, PRIMARY KEY(`_id`), FOREIGN KEY(`_id`) REFERENCES `feeds`(`_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
    db.execSQL("CREATE TABLE IF NOT EXISTS `_new_feeds` (`_id` INTEGER NOT NULL, `url` TEXT NOT NULL, `title` TEXT NOT NULL, `cat_id` INTEGER NOT NULL, `display_title` TEXT NOT NULL, `last_time_update` INTEGER NOT NULL, `unread_count` INTEGER NOT NULL, `is_subscribed` INTEGER NOT NULL, PRIMARY KEY(`_id`), FOREIGN KEY(`cat_id`) REFERENCES `categories`(`_id`) ON UPDATE NO ACTION ON DELETE NO ACTION DEFERRABLE INITIALLY DEFERRED)")
    db.execSQL("INSERT INTO `_new_feeds` (`_id`,`url`,`title`,`cat_id`,`display_title`,`last_time_update`,`unread_count`,`is_subscribed`) SELECT `_id`,`url`,`title`,`cat_id`,`display_title`,`last_time_update`,`unread_count`,`is_subscribed` FROM `feeds`")
    db.execSQL("DROP TABLE `feeds`")
    db.execSQL("ALTER TABLE `_new_feeds` RENAME TO `feeds`")
    db.execSQL("CREATE INDEX IF NOT EXISTS `index_feeds_cat_id` ON `feeds` (`cat_id`)")
    foreignKeyCheck(db, "feeds")
    callback.onPostMigrate(db)
  }
}
