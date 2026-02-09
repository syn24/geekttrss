package com.geekorum.ttrss.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class NetworkModule_ProvidesHttpRequestLogger$app_freeDebugFactory implements Factory<HttpLoggingInterceptor> {
  @Override
  public HttpLoggingInterceptor get() {
    return providesHttpRequestLogger$app_freeDebug();
  }

  public static NetworkModule_ProvidesHttpRequestLogger$app_freeDebugFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static HttpLoggingInterceptor providesHttpRequestLogger$app_freeDebug() {
    return NetworkModule.INSTANCE.providesHttpRequestLogger$app_freeDebug();
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvidesHttpRequestLogger$app_freeDebugFactory INSTANCE = new NetworkModule_ProvidesHttpRequestLogger$app_freeDebugFactory();
  }
}
