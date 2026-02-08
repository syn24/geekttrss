package com.geekorum.ttrss.manage_feeds

import android.content.Context
import androidx.work.WorkManager
import com.geekorum.ttrss.manage_feeds.workers.ManageFeedService
import com.geekorum.ttrss.manage_feeds.workers.RetrofitManageFeedService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManageFeedModule {

    @Binds
    @Singleton
    internal abstract fun bindManageFeedService(
        param: RetrofitManageFeedService
    ): ManageFeedService

    companion object {
        @Provides
        @Singleton
        fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
            return WorkManager.getInstance(context)
        }
    }
}

