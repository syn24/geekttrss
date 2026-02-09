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
import androidx.room.util.createCancellationSignal
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.query
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
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.jvm.JvmStatic

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class TransactionsDao_Impl(
  __db: RoomDatabase,
) : TransactionsDao() {
  private val __db: RoomDatabase

  private val __insertionAdapterOfTransaction: EntityInsertionAdapter<Transaction>

  private val __deletionAdapterOfTransaction: EntityDeletionOrUpdateAdapter<Transaction>
  init {
    this.__db = __db
    this.__insertionAdapterOfTransaction = object : EntityInsertionAdapter<Transaction>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `transactions` (`_id`,`article_id`,`field`,`value`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Transaction) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.articleId)
        statement.bindString(3, entity.field)
        val _tmp: Int = if (entity.value) 1 else 0
        statement.bindLong(4, _tmp.toLong())
      }
    }
    this.__deletionAdapterOfTransaction = object : EntityDeletionOrUpdateAdapter<Transaction>(__db)
        {
      protected override fun createQuery(): String = "DELETE FROM `transactions` WHERE `_id` = ?"

      protected override fun bind(statement: SupportSQLiteStatement, entity: Transaction) {
        statement.bindLong(1, entity.id)
      }
    }
  }

  public override suspend fun insertTransaction(transaction: Transaction): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfTransaction.insert(transaction)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun deleteTransactions(transactions: Collection<Transaction>): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __deletionAdapterOfTransaction.handleMultiple(transactions)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override fun insertUniqueTransaction(transaction: Transaction) {
    __db.beginTransaction()
    try {
      super@TransactionsDao_Impl.insertUniqueTransaction(transaction)
      __db.setTransactionSuccessful()
    } finally {
      __db.endTransaction()
    }
  }

  public override suspend fun getTransactionForArticleAndType(articleId: Long, `field`: String):
      List<Transaction> {
    val _sql: String = "SELECT * FROM transactions WHERE article_id=? AND field=?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 2)
    var _argIndex: Int = 1
    _statement.bindLong(_argIndex, articleId)
    _argIndex = 2
    _statement.bindString(_argIndex, field)
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

  public companion object {
    @JvmStatic
    public fun getRequiredConverters(): List<Class<*>> = emptyList()
  }
}
