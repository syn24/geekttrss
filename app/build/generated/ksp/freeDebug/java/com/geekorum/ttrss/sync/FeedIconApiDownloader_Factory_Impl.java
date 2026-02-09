package com.geekorum.ttrss.sync;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import java.io.File;
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
public final class FeedIconApiDownloader_Factory_Impl implements FeedIconApiDownloader.Factory {
  private final FeedIconApiDownloader_Factory delegateFactory;

  FeedIconApiDownloader_Factory_Impl(FeedIconApiDownloader_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public FeedIconApiDownloader create(File downloadDir) {
    return delegateFactory.get(downloadDir);
  }

  public static Provider<FeedIconApiDownloader.Factory> create(
      FeedIconApiDownloader_Factory delegateFactory) {
    return InstanceFactory.create(new FeedIconApiDownloader_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<FeedIconApiDownloader.Factory> createFactoryProvider(
      FeedIconApiDownloader_Factory delegateFactory) {
    return InstanceFactory.create(new FeedIconApiDownloader_Factory_Impl(delegateFactory));
  }
}
