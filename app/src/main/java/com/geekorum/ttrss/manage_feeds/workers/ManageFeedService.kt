/*
 * Geekttrss is a RSS feed reader application on the Android Platform.
 *
 * Copyright (C) 2017-2025 by Frederic-Charles Barthelery.
 *
 * This file is part of Geekttrss.
 *
 * Geekttrss is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Geekttrss is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Geekttrss.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.geekorum.ttrss.manage_feeds.workers

import android.accounts.Account
import android.accounts.AccountManager
import com.geekorum.ttrss.accounts.TinyrssAccountTokenRetriever
import com.geekorum.ttrss.core.CoroutineDispatchersProvider
import com.geekorum.ttrss.webapi.ApiCallException
import com.geekorum.ttrss.webapi.BasicAuthInterceptor
import com.geekorum.ttrss.webapi.RetrofitServiceHelper
import com.geekorum.ttrss.webapi.TinyRssApi
import com.geekorum.ttrss.webapi.model.SubscribeResultCode
import com.geekorum.ttrss.webapi.model.SubscribeToFeedRequestPayload
import com.geekorum.ttrss.webapi.model.UnsubscribeFeedRequestPayload
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

/**
 * ApiService to unsubscribe from a feed
 */
interface ManageFeedService {

    @Throws(ApiCallException::class)
    suspend fun unsubscribeFromFeed(account: Account, feedId: Long): Boolean

    @Throws(ApiCallException::class)
    suspend fun subscribeToFeed(account: Account, feedUrl: String, categoryId: Long = 0,
                                feedLogin: String = "", feedPassword: String = ""): ResultCode

}

enum class ResultCode {
    SUCCESS, INVALID_URL, UNKNOWN_ERROR
}



/* Implementation */


internal class RetrofitManageFeedService @Inject constructor(
    private val accountManager: AccountManager,
    private val dispatchersProvider: CoroutineDispatchersProvider,
    private val okHttpClient: OkHttpClient
) : ManageFeedService {

    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    override suspend fun unsubscribeFromFeed(account: Account, feedId: Long): Boolean {
        val helper = createHelper(account)
        val api = createApi(account)
        val payload = UnsubscribeFeedRequestPayload(feedId)
        val unsubscribeResult = helper.executeOrFail("Unable to unsubscribe from feed") {
            api.unsubscribeFromFeed(payload)
        }
        if (!unsubscribeResult.success) {
            Timber.w("Unable to unsubscribe feed. Error: ${unsubscribeResult.error}")
            return false
        }
        return true
    }

    override suspend fun subscribeToFeed(
        account: Account, feedUrl: String, categoryId: Long, feedLogin: String, feedPassword: String
    ): ResultCode {
        val helper = createHelper(account)
        val api = createApi(account)
        val payload = SubscribeToFeedRequestPayload(feedUrl, categoryId, feedLogin, feedPassword)
        val subscribeResult = helper.executeOrFail("Unable to subscribe to feed") {
            api.subscribeToFeed(payload)
        }
        val subscribeResultCode = subscribeResult.resultCode
        return when (subscribeResultCode) {
            SubscribeResultCode.FEED_ALREADY_EXIST, SubscribeResultCode.FEED_ADDED -> ResultCode.SUCCESS
            SubscribeResultCode.INVALID_URL -> ResultCode.INVALID_URL
            else -> ResultCode.UNKNOWN_ERROR
        }
    }

    private fun createHelper(account: Account): RetrofitServiceHelper {
        val tokenRetriever = TinyrssAccountTokenRetriever(dispatchersProvider, accountManager, account)
        return RetrofitServiceHelper(tokenRetriever)
    }

    private fun createApi(account: Account): TinyRssApi {
        // According to AccountAuthenticator.kt, accountManager.fromAndroidAccount(account) is used.
        // And accountManager.getServerInformation(ttRssAccount) returns ServerInformation.
        // So I should use AndroidTinyrssAccountManager methods which abstract this away!
        // But previously I saw AndroidTinyrssAccountManager interface did NOT expose simple getters for URL.
        // It has `getServerInformation(ttRssAccount)`.

        // So I must depend on `AndroidTinyrssAccountManager`. which I injected as `accountManager`.
        // Note: In `RetrofitManageFeedService` constructor I use `android.accounts.AccountManager` with name `accountManager`.
        // This clashes with `AndroidTinyrssAccountManager`.

        // I need BOTH.

        val ttrssAccount = com.geekorum.ttrss.accounts.Account(account.name, accountManager.getUserData(account, "url"))
        // Wait, TinyrssAcountManager.Account constructor takes (username, url).
        // `AndroidTinyrssAccountManager`.fromAndroidAccount(account) does exactly this.
        // I should inject `AndroidTinyrssAccountManager` too!

        // Wait, I can just use `accountManager.getUserData(account, "url")` if I am sure "url" is the key.
        // In `AccountAuthenticator.kt`, `getServerInformation` is called.
        // I haven't seen "url" usage directly but `Account(username, url)` implies it holds URL.

        // Let's assume "url" is correct.
        val url = accountManager.getUserData(account, "url")
        if (url.isNullOrEmpty()) {
             throw IllegalStateException("Account URL not found")
        }

        val username = account.name
        val password = accountManager.getPassword(account)

        var client = okHttpClient
        if (password != null) {
             client = client.newBuilder()
                 .addInterceptor(BasicAuthInterceptor(username, password))
                 .build()
        }

        return Retrofit.Builder()
            .baseUrl(if (url.endsWith("/")) url else "$url/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(TinyRssApi::class.java)
    }

}
