package com.geekorum.ttrss.di;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.Cache;

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
public final class NetworkModule_ProvidesCache$app_freeDebugFactory implements Factory<Cache> {
  private final Provider<Application> applicationProvider;

  private NetworkModule_ProvidesCache$app_freeDebugFactory(
      Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public Cache get() {
    return providesCache$app_freeDebug(applicationProvider.get());
  }

  public static NetworkModule_ProvidesCache$app_freeDebugFactory create(
      Provider<Application> applicationProvider) {
    return new NetworkModule_ProvidesCache$app_freeDebugFactory(applicationProvider);
  }

  public static Cache providesCache$app_freeDebug(Application application) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providesCache$app_freeDebug(application));
  }
}
