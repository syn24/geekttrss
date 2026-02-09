package com.geekorum.ttrss.di;

import com.geekorum.ttrss.logging.RetrofitInvocationLogger;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvidesOkHttpclient$app_freeDebugFactory implements Factory<OkHttpClient> {
  private final Provider<Cache> cacheProvider;

  private final Provider<HttpLoggingInterceptor> requestLoggerProvider;

  private final Provider<RetrofitInvocationLogger> retrofitInvocationLoggerProvider;

  private NetworkModule_ProvidesOkHttpclient$app_freeDebugFactory(Provider<Cache> cacheProvider,
      Provider<HttpLoggingInterceptor> requestLoggerProvider,
      Provider<RetrofitInvocationLogger> retrofitInvocationLoggerProvider) {
    this.cacheProvider = cacheProvider;
    this.requestLoggerProvider = requestLoggerProvider;
    this.retrofitInvocationLoggerProvider = retrofitInvocationLoggerProvider;
  }

  @Override
  public OkHttpClient get() {
    return providesOkHttpclient$app_freeDebug(cacheProvider.get(), requestLoggerProvider.get(), retrofitInvocationLoggerProvider.get());
  }

  public static NetworkModule_ProvidesOkHttpclient$app_freeDebugFactory create(
      Provider<Cache> cacheProvider, Provider<HttpLoggingInterceptor> requestLoggerProvider,
      Provider<RetrofitInvocationLogger> retrofitInvocationLoggerProvider) {
    return new NetworkModule_ProvidesOkHttpclient$app_freeDebugFactory(cacheProvider, requestLoggerProvider, retrofitInvocationLoggerProvider);
  }

  public static OkHttpClient providesOkHttpclient$app_freeDebug(Cache cache,
      HttpLoggingInterceptor requestLogger, RetrofitInvocationLogger retrofitInvocationLogger) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providesOkHttpclient$app_freeDebug(cache, requestLogger, retrofitInvocationLogger));
  }
}
