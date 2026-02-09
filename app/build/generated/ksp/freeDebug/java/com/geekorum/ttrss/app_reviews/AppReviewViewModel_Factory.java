package com.geekorum.ttrss.app_reviews;

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
public final class AppReviewViewModel_Factory implements Factory<AppReviewViewModel> {
  private final Provider<AppReviewStateManager> appReviewStateManagerProvider;

  private final Provider<AppReviewManager> reviewManagerProvider;

  private AppReviewViewModel_Factory(Provider<AppReviewStateManager> appReviewStateManagerProvider,
      Provider<AppReviewManager> reviewManagerProvider) {
    this.appReviewStateManagerProvider = appReviewStateManagerProvider;
    this.reviewManagerProvider = reviewManagerProvider;
  }

  @Override
  public AppReviewViewModel get() {
    return newInstance(appReviewStateManagerProvider.get(), reviewManagerProvider.get());
  }

  public static AppReviewViewModel_Factory create(
      Provider<AppReviewStateManager> appReviewStateManagerProvider,
      Provider<AppReviewManager> reviewManagerProvider) {
    return new AppReviewViewModel_Factory(appReviewStateManagerProvider, reviewManagerProvider);
  }

  public static AppReviewViewModel newInstance(AppReviewStateManager appReviewStateManager,
      AppReviewManager reviewManager) {
    return new AppReviewViewModel(appReviewStateManager, reviewManager);
  }
}
