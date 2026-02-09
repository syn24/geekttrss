package com.geekorum.ttrss.sync;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class FeedIconSynchronizer_Factory_Impl implements FeedIconSynchronizer.Factory {
  private final FeedIconSynchronizer_Factory delegateFactory;

  FeedIconSynchronizer_Factory_Impl(FeedIconSynchronizer_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public FeedIconSynchronizer create(FeedIconApiDownloader feedIconApiDownloader) {
    return delegateFactory.get(feedIconApiDownloader);
  }

  public static Provider<FeedIconSynchronizer.Factory> create(
      FeedIconSynchronizer_Factory delegateFactory) {
    return InstanceFactory.create(new FeedIconSynchronizer_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<FeedIconSynchronizer.Factory> createFactoryProvider(
      FeedIconSynchronizer_Factory delegateFactory) {
    return InstanceFactory.create(new FeedIconSynchronizer_Factory_Impl(delegateFactory));
  }
}
