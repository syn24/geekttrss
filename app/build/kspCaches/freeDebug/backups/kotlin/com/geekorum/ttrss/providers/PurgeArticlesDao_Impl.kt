package com.geekorum.ttrss.providers

import androidx.room.CoroutinesRoom
import androidx.room.RoomDatabase
import androidx.room.SharedSQLiteStatement
import androidx.sqlite.db.SupportSQLiteStatement
import java.lang.Class
import java.util.concurrent.Callable
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.jvm.JvmStatic

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class PurgeArticlesDao_Impl(
  __db: RoomDatabase,
) : PurgeArticlesDao {
  private val __db: RoomDatabase

  private val __preparedStmtOfDeleteNonImportantArticlesBeforeTime: SharedSQLiteStatement
  init {
    this.__db = __db
    this.__preparedStmtOfDeleteNonImportantArticlesBeforeTime = object : SharedSQLiteStatement(__db)
        {
      public override fun createQuery(): String {
        val _query: String =
            "DELETE FROM articles WHERE unread=0 AND marked=0 AND published=0 AND last_time_update<=? AND _id NOT IN (SELECT MAX(_id) FROM articles GROUP BY feed_id)"
        return _query
      }
    }
  }

  public override suspend fun deleteNonImportantArticlesBeforeTime(beforeTimeSec: Long): Int =
      CoroutinesRoom.execute(__db, true, object : Callable<Int> {
    public override fun call(): Int {
      val _stmt: SupportSQLiteStatement =
          __preparedStmtOfDeleteNonImportantArticlesBeforeTime.acquire()
      var _argIndex: Int = 1
      _stmt.bindLong(_argIndex, beforeTimeSec)
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
        __preparedStmtOfDeleteNonImportantArticlesBeforeTime.release(_stmt)
      }
    }
  })

  public companion object {
    @JvmStatic
    public fun getRequiredConverters(): List<Class<*>> = emptyList()
  }
}
