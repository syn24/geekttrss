package com.geekorum.ttrss.network;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import com.geekorum.geekdroid.network.BrowserLauncher;
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
public final class TtRssBrowserLauncher_Factory implements Factory<TtRssBrowserLauncher> {
  private final Provider<BrowserLauncher> delegateProvider;

  private final Provider<SharedPreferences> appPreferencesProvider;

  private final Provider<PackageManager> packageManagerProvider;

  private TtRssBrowserLauncher_Factory(Provider<BrowserLauncher> delegateProvider,
      Provider<SharedPreferences> appPreferencesProvider,
      Provider<PackageManager> packageManagerProvider) {
    this.delegateProvider = delegateProvider;
    this.appPreferencesProvider = appPreferencesProvider;
    this.packageManagerProvider = packageManagerProvider;
  }

  @Override
  public TtRssBrowserLauncher get() {
    return newInstance(delegateProvider.get(), appPreferencesProvider.get(), packageManagerProvider.get());
  }

  public static TtRssBrowserLauncher_Factory create(Provider<BrowserLauncher> delegateProvider,
      Provider<SharedPreferences> appPreferencesProvider,
      Provider<PackageManager> packageManagerProvider) {
    return new TtRssBrowserLauncher_Factory(delegateProvider, appPreferencesProvider, packageManagerProvider);
  }

  public static TtRssBrowserLauncher newInstance(BrowserLauncher delegate,
      SharedPreferences appPreferences, PackageManager packageManager) {
    return new TtRssBrowserLauncher(delegate, appPreferences, packageManager);
  }
}
