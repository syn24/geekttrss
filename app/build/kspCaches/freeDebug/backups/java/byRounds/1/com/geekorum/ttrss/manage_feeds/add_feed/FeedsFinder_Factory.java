package com.geekorum.ttrss.manage_feeds.add_feed;

import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import com.geekorum.ttrss.htmlparsers.FeedExtractor;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class FeedsFinder_Factory implements Factory<FeedsFinder> {
  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<FeedExtractor> feedExtractorProvider;

  private FeedsFinder_Factory(Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<OkHttpClient> okHttpClientProvider, Provider<FeedExtractor> feedExtractorProvider) {
    this.dispatchersProvider = dispatchersProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.feedExtractorProvider = feedExtractorProvider;
  }

  @Override
  public FeedsFinder get() {
    return newInstance(dispatchersProvider.get(), okHttpClientProvider.get(), feedExtractorProvider.get());
  }

  public static FeedsFinder_Factory create(
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<OkHttpClient> okHttpClientProvider, Provider<FeedExtractor> feedExtractorProvider) {
    return new FeedsFinder_Factory(dispatchersProvider, okHttpClientProvider, feedExtractorProvider);
  }

  public static FeedsFinder newInstance(CoroutineDispatchersProvider dispatchers,
      OkHttpClient okHttpClient, FeedExtractor feedExtractor) {
    return new FeedsFinder(dispatchers, okHttpClient, feedExtractor);
  }
}
