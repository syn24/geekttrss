package com.geekorum.ttrss.`data`

import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.foreignKeyCheck
import androidx.sqlite.db.SupportSQLiteDatabase
import com.geekorum.ttrss.`data`.migrations.MigrationFrom14To15
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
internal class ArticlesDatabase_AutoMigration_14_15_Impl : Migration {
  private val callback: AutoMigrationSpec = MigrationFrom14To15()

  public constructor() : super(14, 15)

  public override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL("CREATE TABLE IF NOT EXISTS `_new_articles` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `unread` INTEGER NOT NULL, `transiant_unread` INTEGER NOT NULL, `marked` INTEGER NOT NULL, `published` INTEGER NOT NULL, `score` INTEGER NOT NULL, `last_time_update` INTEGER NOT NULL, `is_updated` INTEGER NOT NULL, `link` TEXT NOT NULL, `feed_id` INTEGER NOT NULL, `flavor_image_uri` TEXT NOT NULL, `content_excerpt` TEXT NOT NULL, `title` TEXT NOT NULL, `tags` TEXT NOT NULL, `content` TEXT NOT NULL, `author` TEXT NOT NULL, FOREIGN KEY(`feed_id`) REFERENCES `feeds`(`_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
    db.execSQL("INSERT INTO `_new_articles` (`_id`,`unread`,`transiant_unread`,`marked`,`published`,`score`,`last_time_update`,`is_updated`,`link`,`feed_id`,`flavor_image_uri`,`content_excerpt`,`title`,`tags`,`content`,`author`) SELECT `_id`,`unread`,`transiant_unread`,`marked`,`published`,`score`,`last_time_update`,`is_updated`,`link`,`feed_id`,`flavor_image_uri`,`content_excerpt`,`title`,`tags`,`content`,`author` FROM `articles`")
    db.execSQL("DROP TABLE `articles`")
    db.execSQL("ALTER TABLE `_new_articles` RENAME TO `articles`")
    db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_unread_last_time_update` ON `articles` (`unread`, `last_time_update`)")
    db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_feed_id_unread_last_time_update` ON `articles` (`feed_id`, `unread`, `last_time_update`)")
    db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_marked_unread_last_time_update` ON `articles` (`marked`, `unread`, `last_time_update`)")
    db.execSQL("CREATE INDEX IF NOT EXISTS `index_articles_feed_id` ON `articles` (`feed_id`)")
    foreignKeyCheck(db, "articles")
    callback.onPostMigrate(db)
  }
}
