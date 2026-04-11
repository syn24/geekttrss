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
package com.geekorum.ttrss.accounts

import android.accounts.AccountManager
import android.content.ContentResolver
import android.os.Bundle
import androidx.test.core.app.ApplicationProvider
import androidx.work.testing.WorkManagerTestInitHelper
import com.geekorum.geekdroid.security.SecretCipher
import com.geekorum.geekdroid.security.SecretEncryption
import com.geekorum.ttrss.providers.ArticlesContract
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class AndroidTinyrssAccountManagerTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    lateinit var accountManager: AndroidTinyrssAccountManager
    lateinit var androidAccountManager: AccountManager
    lateinit var secretCipher: SecretCipher

    private val modelAccount = Account("test", "https://exemple.com")
    private val androidAccount = android.accounts.Account("test", AndroidTinyrssAccountManager.ACCOUNT_TYPE)

    @Before
    fun setup() {
        // sync get triggered during test and try to access WorkManager from HiltTestApplication
        // provide a TestWorkManager
        WorkManagerTestInitHelper.initializeTestWorkManager(ApplicationProvider.getApplicationContext())
        androidAccountManager = AccountManager.get(ApplicationProvider.getApplicationContext())
        secretCipher = SecretEncryption().getSecretCipher("instrumented test")
        accountManager = AndroidTinyrssAccountManager(androidAccountManager, secretCipher)
        // Ensure no leftover "test" account from a previous run or test — otherwise
        // addAccountExplicitly below returns false because the account already exists.
        androidAccountManager.removeAccountExplicitly(androidAccount)
    }


    @After
    fun tearDown() {
        androidAccountManager.removeAccountExplicitly(androidAccount)
    }

    @Test
    fun testThatAddAccountWorks() {
        val account = modelAccount
        val password = "password"
        val result = accountManager.addAccount(account, password)
        assertThat(result).isTrue()
        assertThat(androidAccountManager.getAccountsByType(AndroidTinyrssAccountManager.ACCOUNT_TYPE)).asList().contains(
            androidAccount)
        val retrievedPassword = accountManager.getPassword(account)
        assertThat(retrievedPassword).isEqualTo(password)
    }

    @Test
    fun testThatAccountWithLongPasswordWorks() {
        val account = modelAccount
        val password = "9a266fc8b42966fb624d852bafa241d8fd05b47d36153ff6684ab344bd1ae57bba96a7de8fc12ec0bb016583735d7f5bca6dd7d9bc6482c2a3ac6bf6f9ec323f"
        val result = accountManager.addAccount(account, password)
        assertThat(result).isTrue()
        assertThat(androidAccountManager.getAccountsByType(AndroidTinyrssAccountManager.ACCOUNT_TYPE)).asList().contains(
            androidAccount)
        val retrievedPassword = accountManager.getPassword(account)
        assertThat(retrievedPassword).isEqualTo(password)
    }


    @Test
    fun testThatInitializeAccountSyncWorks() {
        val urlBundle = Bundle().apply {
            putString(AccountAuthenticator.USERDATA_URL, "https://exemple.com")
        }
        androidAccountManager.addAccountExplicitly(androidAccount, "password", urlBundle)

        accountManager.initializeAccountSync(modelAccount)
        // the system needs some time to initialize
        runBlocking { delay(1000) }

        // initializeAccountSync now only enables sync-on-demand via setSyncAutomatically.
        // Periodic sync scheduling was moved to WorkManager (BackgroundJobManager.setupPeriodicRefresh)
        // for reliability on devices with aggressive battery optimization.
        val syncAutomatically = ContentResolver.getSyncAutomatically(androidAccount, ArticlesContract.AUTHORITY)
        assertThat(syncAutomatically).isTrue()
    }
}
