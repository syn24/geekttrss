package com.geekorum.ttrss.manage_feeds;

import com.geekorum.ttrss.data.ManageFeedsDao;
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
public final class ManageFeedViewModel_Factory implements Factory<ManageFeedViewModel> {
  private final Provider<ManageFeedsDao> feedsDaoProvider;

  private ManageFeedViewModel_Factory(Provider<ManageFeedsDao> feedsDaoProvider) {
    this.feedsDaoProvider = feedsDaoProvider;
  }

  @Override
  public ManageFeedViewModel get() {
    return newInstance(feedsDaoProvider.get());
  }

  public static ManageFeedViewModel_Factory create(Provider<ManageFeedsDao> feedsDaoProvider) {
    return new ManageFeedViewModel_Factory(feedsDaoProvider);
  }

  public static ManageFeedViewModel newInstance(ManageFeedsDao feedsDao) {
    return new ManageFeedViewModel(feedsDao);
  }
}
