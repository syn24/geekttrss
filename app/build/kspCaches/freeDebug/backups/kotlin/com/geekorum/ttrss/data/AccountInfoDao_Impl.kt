package com.geekorum.ttrss.`data`

import android.database.Cursor
import android.os.CancellationSignal
import androidx.room.CoroutinesRoom
import androidx.room.CoroutinesRoom.Companion.execute
import androidx.room.EntityInsertionAdapter
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.RoomSQLiteQuery.Companion.acquire
import androidx.room.util.createCancellationSignal
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.query
import androidx.sqlite.db.SupportSQLiteStatement
import java.lang.Class
import java.util.concurrent.Callable
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.jvm.JvmStatic

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION"])
public class AccountInfoDao_Impl(
  __db: RoomDatabase,
) : AccountInfoDao {
  private val __db: RoomDatabase

  private val __insertionAdapterOfAccountInfo: EntityInsertionAdapter<AccountInfo>
  init {
    this.__db = __db
    this.__insertionAdapterOfAccountInfo = object : EntityInsertionAdapter<AccountInfo>(__db) {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `account_info` (`server_version`,`api_level`,`account_username`,`account_url`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SupportSQLiteStatement, entity: AccountInfo) {
        statement.bindString(1, entity.serverVersion)
        statement.bindLong(2, entity.apiLevel.toLong())
        val _tmpAccount: Account = entity.account
        statement.bindString(3, _tmpAccount.username)
        statement.bindString(4, _tmpAccount.url)
      }
    }
  }

  public override suspend fun insertAccountInfo(accountInfo: AccountInfo): Unit =
      CoroutinesRoom.execute(__db, true, object : Callable<Unit> {
    public override fun call() {
      __db.beginTransaction()
      try {
        __insertionAdapterOfAccountInfo.insert(accountInfo)
        __db.setTransactionSuccessful()
      } finally {
        __db.endTransaction()
      }
    }
  })

  public override suspend fun getAccountInfo(username: String, url: String): AccountInfo? {
    val _sql: String = "SELECT * FROM account_info WHERE account_username=? AND account_url=?"
    val _statement: RoomSQLiteQuery = acquire(_sql, 2)
    var _argIndex: Int = 1
    _statement.bindString(_argIndex, username)
    _argIndex = 2
    _statement.bindString(_argIndex, url)
    val _cancellationSignal: CancellationSignal? = createCancellationSignal()
    return execute(__db, false, _cancellationSignal, object : Callable<AccountInfo?> {
      public override fun call(): AccountInfo? {
        val _cursor: Cursor = query(__db, _statement, false, null)
        try {
          val _cursorIndexOfServerVersion: Int = getColumnIndexOrThrow(_cursor, "server_version")
          val _cursorIndexOfApiLevel: Int = getColumnIndexOrThrow(_cursor, "api_level")
          val _cursorIndexOfUsername: Int = getColumnIndexOrThrow(_cursor, "account_username")
          val _cursorIndexOfUrl: Int = getColumnIndexOrThrow(_cursor, "account_url")
          val _result: AccountInfo?
          if (_cursor.moveToFirst()) {
            val _tmpServerVersion: String
            _tmpServerVersion = _cursor.getString(_cursorIndexOfServerVersion)
            val _tmpApiLevel: Int
            _tmpApiLevel = _cursor.getInt(_cursorIndexOfApiLevel)
            val _tmpAccount: Account
            val _tmpUsername: String
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername)
            val _tmpUrl: String
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl)
            _tmpAccount = Account(_tmpUsername,_tmpUrl)
            _result = AccountInfo(_tmpAccount,_tmpServerVersion,_tmpApiLevel)
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

  public companion object {
    @JvmStatic
    public fun getRequiredConverters(): List<Class<*>> = emptyList()
  }
}
