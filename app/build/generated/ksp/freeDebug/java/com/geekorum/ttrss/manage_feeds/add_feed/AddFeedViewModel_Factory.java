package com.geekorum.ttrss.manage_feeds.add_feed;

import android.accounts.AccountManager;
import androidx.work.WorkManager;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class AddFeedViewModel_Factory implements Factory<AddFeedViewModel> {
  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<FeedsFinder> feedsFinderProvider;

  private final Provider<WorkManager> workManagerProvider;

  private final Provider<AccountManager> accountManagerProvider;

  private AddFeedViewModel_Factory(Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<FeedsFinder> feedsFinderProvider, Provider<WorkManager> workManagerProvider,
      Provider<AccountManager> accountManagerProvider) {
    this.dispatchersProvider = dispatchersProvider;
    this.feedsFinderProvider = feedsFinderProvider;
    this.workManagerProvider = workManagerProvider;
    this.accountManagerProvider = accountManagerProvider;
  }

  @Override
  public AddFeedViewModel get() {
    return newInstance(dispatchersProvider.get(), feedsFinderProvider.get(), workManagerProvider.get(), accountManagerProvider.get());
  }

  public static AddFeedViewModel_Factory create(
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<FeedsFinder> feedsFinderProvider, Provider<WorkManager> workManagerProvider,
      Provider<AccountManager> accountManagerProvider) {
    return new AddFeedViewModel_Factory(dispatchersProvider, feedsFinderProvider, workManagerProvider, accountManagerProvider);
  }

  public static AddFeedViewModel newInstance(CoroutineDispatchersProvider dispatchers,
      FeedsFinder feedsFinder, WorkManager workManager, AccountManager accountManager) {
    return new AddFeedViewModel(dispatchers, feedsFinder, workManager, accountManager);
  }
}
