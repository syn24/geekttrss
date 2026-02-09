package com.geekorum.ttrss.app_reviews;

import android.content.SharedPreferences;
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
public final class AppReviewStateManager_Factory implements Factory<AppReviewStateManager> {
  private final Provider<SharedPreferences> appPreferencesProvider;

  private AppReviewStateManager_Factory(Provider<SharedPreferences> appPreferencesProvider) {
    this.appPreferencesProvider = appPreferencesProvider;
  }

  @Override
  public AppReviewStateManager get() {
    return newInstance(appPreferencesProvider.get());
  }

  public static AppReviewStateManager_Factory create(
      Provider<SharedPreferences> appPreferencesProvider) {
    return new AppReviewStateManager_Factory(appPreferencesProvider);
  }

  public static AppReviewStateManager newInstance(SharedPreferences appPreferences) {
    return new AppReviewStateManager(appPreferences);
  }
}
