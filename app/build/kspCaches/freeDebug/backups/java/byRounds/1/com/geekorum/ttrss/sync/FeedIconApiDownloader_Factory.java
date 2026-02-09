package com.geekorum.ttrss.sync;

import com.geekorum.ttrss.network.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.io.File;
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
public final class FeedIconApiDownloader_Factory {
  private final Provider<ApiService> apiServiceProvider;

  private FeedIconApiDownloader_Factory(Provider<ApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  public FeedIconApiDownloader get(File downloadDir) {
    return newInstance(apiServiceProvider.get(), downloadDir);
  }

  public static FeedIconApiDownloader_Factory create(Provider<ApiService> apiServiceProvider) {
    return new FeedIconApiDownloader_Factory(apiServiceProvider);
  }

  public static FeedIconApiDownloader newInstance(ApiService apiService, File downloadDir) {
    return new FeedIconApiDownloader(apiService, downloadDir);
  }
}
