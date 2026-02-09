package com.geekorum.ttrss.di;

import android.app.Application;
import coil.ImageLoader;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvidesImageLoader$app_freeDebugFactory implements Factory<ImageLoader> {
  private final Provider<Application> applicationProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private NetworkModule_ProvidesImageLoader$app_freeDebugFactory(
      Provider<Application> applicationProvider, Provider<OkHttpClient> okHttpClientProvider) {
    this.applicationProvider = applicationProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public ImageLoader get() {
    return providesImageLoader$app_freeDebug(applicationProvider.get(), okHttpClientProvider.get());
  }

  public static NetworkModule_ProvidesImageLoader$app_freeDebugFactory create(
      Provider<Application> applicationProvider, Provider<OkHttpClient> okHttpClientProvider) {
    return new NetworkModule_ProvidesImageLoader$app_freeDebugFactory(applicationProvider, okHttpClientProvider);
  }

  public static ImageLoader providesImageLoader$app_freeDebug(Application application,
      OkHttpClient okHttpClient) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providesImageLoader$app_freeDebug(application, okHttpClient));
  }
}
