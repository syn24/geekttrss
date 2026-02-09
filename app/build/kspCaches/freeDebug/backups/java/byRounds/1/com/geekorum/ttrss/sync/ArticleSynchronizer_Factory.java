package com.geekorum.ttrss.sync;

import android.accounts.Account;
import android.app.Application;
import android.os.Bundle;
import com.geekorum.ttrss.data.feedsettings.FeedSettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ArticleSynchronizer_Factory {
  private final Provider<Application> applicationProvider;

  private final Provider<Account> accountProvider;

  private final Provider<DatabaseService> databaseServiceProvider;

  private final Provider<FeedSettingsRepository> feedSettingsRepositoryProvider;

  private ArticleSynchronizer_Factory(Provider<Application> applicationProvider,
      Provider<Account> accountProvider, Provider<DatabaseService> databaseServiceProvider,
      Provider<FeedSettingsRepository> feedSettingsRepositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.accountProvider = accountProvider;
    this.databaseServiceProvider = databaseServiceProvider;
    this.feedSettingsRepositoryProvider = feedSettingsRepositoryProvider;
  }

  public ArticleSynchronizer get(Bundle params) {
    return newInstance(applicationProvider.get(), params, accountProvider.get(), databaseServiceProvider.get(), feedSettingsRepositoryProvider.get());
  }

  public static ArticleSynchronizer_Factory create(Provider<Application> applicationProvider,
      Provider<Account> accountProvider, Provider<DatabaseService> databaseServiceProvider,
      Provider<FeedSettingsRepository> feedSettingsRepositoryProvider) {
    return new ArticleSynchronizer_Factory(applicationProvider, accountProvider, databaseServiceProvider, feedSettingsRepositoryProvider);
  }

  public static ArticleSynchronizer newInstance(Application application, Bundle params,
      Account account, DatabaseService databaseService,
      FeedSettingsRepository feedSettingsRepository) {
    return new ArticleSynchronizer(application, params, account, databaseService, feedSettingsRepository);
  }
}
