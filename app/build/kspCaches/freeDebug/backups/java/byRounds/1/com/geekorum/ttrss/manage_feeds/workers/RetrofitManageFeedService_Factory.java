package com.geekorum.ttrss.manage_feeds.workers;

import android.accounts.AccountManager;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
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
public final class RetrofitManageFeedService_Factory implements Factory<RetrofitManageFeedService> {
  private final Provider<AccountManager> accountManagerProvider;

  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private RetrofitManageFeedService_Factory(Provider<AccountManager> accountManagerProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.accountManagerProvider = accountManagerProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public RetrofitManageFeedService get() {
    return newInstance(accountManagerProvider.get(), dispatchersProvider.get(), okHttpClientProvider.get());
  }

  public static RetrofitManageFeedService_Factory create(
      Provider<AccountManager> accountManagerProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new RetrofitManageFeedService_Factory(accountManagerProvider, dispatchersProvider, okHttpClientProvider);
  }

  public static RetrofitManageFeedService newInstance(AccountManager accountManager,
      CoroutineDispatchersProvider dispatchersProvider, OkHttpClient okHttpClient) {
    return new RetrofitManageFeedService(accountManager, dispatchersProvider, okHttpClient);
  }
}
