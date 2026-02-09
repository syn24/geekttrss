package com.geekorum.ttrss.app_reviews;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class NoAppReviewModule_ProvidesAppReviewManagerFactory implements Factory<AppReviewManager> {
  @Override
  public AppReviewManager get() {
    return providesAppReviewManager();
  }

  public static NoAppReviewModule_ProvidesAppReviewManagerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppReviewManager providesAppReviewManager() {
    return Preconditions.checkNotNullFromProvides(NoAppReviewModule.INSTANCE.providesAppReviewManager());
  }

  private static final class InstanceHolder {
    static final NoAppReviewModule_ProvidesAppReviewManagerFactory INSTANCE = new NoAppReviewModule_ProvidesAppReviewManagerFactory();
  }
}
