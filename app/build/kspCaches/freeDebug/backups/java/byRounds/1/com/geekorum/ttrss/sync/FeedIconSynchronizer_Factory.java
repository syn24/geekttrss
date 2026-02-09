package com.geekorum.ttrss.sync;

import android.security.NetworkSecurityPolicy;
import com.geekorum.favikonsnoop.FaviKonSnoop;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import com.geekorum.ttrss.network.ApiService;
import dagger.internal.DaggerGenerated;
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
public final class FeedIconSynchronizer_Factory {
  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<DatabaseService> databaseServiceProvider;

  private final Provider<ApiService> apiServiceProvider;

  private final Provider<FaviKonSnoop> faviKonSnoopProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<HttpCacher> httpCacherProvider;

  private final Provider<NetworkSecurityPolicy> networkSecurityPolicyProvider;

  private FeedIconSynchronizer_Factory(Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<DatabaseService> databaseServiceProvider, Provider<ApiService> apiServiceProvider,
      Provider<FaviKonSnoop> faviKonSnoopProvider, Provider<OkHttpClient> okHttpClientProvider,
      Provider<HttpCacher> httpCacherProvider,
      Provider<NetworkSecurityPolicy> networkSecurityPolicyProvider) {
    this.dispatchersProvider = dispatchersProvider;
    this.databaseServiceProvider = databaseServiceProvider;
    this.apiServiceProvider = apiServiceProvider;
    this.faviKonSnoopProvider = faviKonSnoopProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.httpCacherProvider = httpCacherProvider;
    this.networkSecurityPolicyProvider = networkSecurityPolicyProvider;
  }

  public FeedIconSynchronizer get(FeedIconApiDownloader feedIconApiDownloader) {
    return newInstance(dispatchersProvider.get(), databaseServiceProvider.get(), apiServiceProvider.get(), faviKonSnoopProvider.get(), okHttpClientProvider.get(), httpCacherProvider.get(), networkSecurityPolicyProvider.get(), feedIconApiDownloader);
  }

  public static FeedIconSynchronizer_Factory create(
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<DatabaseService> databaseServiceProvider, Provider<ApiService> apiServiceProvider,
      Provider<FaviKonSnoop> faviKonSnoopProvider, Provider<OkHttpClient> okHttpClientProvider,
      Provider<HttpCacher> httpCacherProvider,
      Provider<NetworkSecurityPolicy> networkSecurityPolicyProvider) {
    return new FeedIconSynchronizer_Factory(dispatchersProvider, databaseServiceProvider, apiServiceProvider, faviKonSnoopProvider, okHttpClientProvider, httpCacherProvider, networkSecurityPolicyProvider);
  }

  public static FeedIconSynchronizer newInstance(CoroutineDispatchersProvider dispatchers,
      DatabaseService databaseService, ApiService apiService, FaviKonSnoop faviKonSnoop,
      OkHttpClient okHttpClient, HttpCacher httpCacher, NetworkSecurityPolicy networkSecurityPolicy,
      FeedIconApiDownloader feedIconApiDownloader) {
    return new FeedIconSynchronizer(dispatchers, databaseService, apiService, faviKonSnoop, okHttpClient, httpCacher, networkSecurityPolicy, feedIconApiDownloader);
  }
}
