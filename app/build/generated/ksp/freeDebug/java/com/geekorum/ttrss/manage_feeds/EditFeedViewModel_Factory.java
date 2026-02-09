package com.geekorum.ttrss.manage_feeds;

import android.accounts.AccountManager;
import android.app.Application;
import com.geekorum.ttrss.data.ManageFeedsDao;
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
public final class EditFeedViewModel_Factory {
  private final Provider<Application> applicationProvider;

  private final Provider<AccountManager> accountManagerProvider;

  private final Provider<ManageFeedsDao> feedsDaoProvider;

  private final Provider<FeedSettingsRepository> feedSettingsRepositoryProvider;

  private EditFeedViewModel_Factory(Provider<Application> applicationProvider,
      Provider<AccountManager> accountManagerProvider, Provider<ManageFeedsDao> feedsDaoProvider,
      Provider<FeedSettingsRepository> feedSettingsRepositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.accountManagerProvider = accountManagerProvider;
    this.feedsDaoProvider = feedsDaoProvider;
    this.feedSettingsRepositoryProvider = feedSettingsRepositoryProvider;
  }

  public EditFeedViewModel get(long feedId) {
    return newInstance(feedId, applicationProvider.get(), accountManagerProvider.get(), feedsDaoProvider.get(), feedSettingsRepositoryProvider.get());
  }

  public static EditFeedViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<AccountManager> accountManagerProvider, Provider<ManageFeedsDao> feedsDaoProvider,
      Provider<FeedSettingsRepository> feedSettingsRepositoryProvider) {
    return new EditFeedViewModel_Factory(applicationProvider, accountManagerProvider, feedsDaoProvider, feedSettingsRepositoryProvider);
  }

  public static EditFeedViewModel newInstance(long feedId, Application application,
      AccountManager accountManager, ManageFeedsDao feedsDao,
      FeedSettingsRepository feedSettingsRepository) {
    return new EditFeedViewModel(feedId, application, accountManager, feedsDao, feedSettingsRepository);
  }
}
