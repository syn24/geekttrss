package com.geekorum.ttrss.sync;

import android.net.ConnectivityManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class BackgroundDataUsageManager_Factory implements Factory<BackgroundDataUsageManager> {
  private final Provider<ConnectivityManager> connectivityManagerProvider;

  private BackgroundDataUsageManager_Factory(
      Provider<ConnectivityManager> connectivityManagerProvider) {
    this.connectivityManagerProvider = connectivityManagerProvider;
  }

  @Override
  public BackgroundDataUsageManager get() {
    return newInstance(connectivityManagerProvider.get());
  }

  public static BackgroundDataUsageManager_Factory create(
      Provider<ConnectivityManager> connectivityManagerProvider) {
    return new BackgroundDataUsageManager_Factory(connectivityManagerProvider);
  }

  public static BackgroundDataUsageManager newInstance(ConnectivityManager connectivityManager) {
    return new BackgroundDataUsageManager(connectivityManager);
  }
}
