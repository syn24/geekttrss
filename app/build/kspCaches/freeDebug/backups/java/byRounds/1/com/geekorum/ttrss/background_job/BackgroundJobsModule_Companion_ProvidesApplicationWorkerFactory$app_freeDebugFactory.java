package com.geekorum.ttrss.background_job;

import androidx.hilt.work.HiltWorkerFactory;
import androidx.work.WorkerFactory;
import com.geekorum.ttrss.features_api.FeaturesWorkerFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class BackgroundJobsModule_Companion_ProvidesApplicationWorkerFactory$app_freeDebugFactory implements Factory<WorkerFactory> {
  private final Provider<HiltWorkerFactory> hiltWorkerFactoryProvider;

  private final Provider<FeaturesWorkerFactory> featuresWorkerFactoryProvider;

  private BackgroundJobsModule_Companion_ProvidesApplicationWorkerFactory$app_freeDebugFactory(
      Provider<HiltWorkerFactory> hiltWorkerFactoryProvider,
      Provider<FeaturesWorkerFactory> featuresWorkerFactoryProvider) {
    this.hiltWorkerFactoryProvider = hiltWorkerFactoryProvider;
    this.featuresWorkerFactoryProvider = featuresWorkerFactoryProvider;
  }

  @Override
  public WorkerFactory get() {
    return providesApplicationWorkerFactory$app_freeDebug(hiltWorkerFactoryProvider.get(), featuresWorkerFactoryProvider.get());
  }

  public static BackgroundJobsModule_Companion_ProvidesApplicationWorkerFactory$app_freeDebugFactory create(
      Provider<HiltWorkerFactory> hiltWorkerFactoryProvider,
      Provider<FeaturesWorkerFactory> featuresWorkerFactoryProvider) {
    return new BackgroundJobsModule_Companion_ProvidesApplicationWorkerFactory$app_freeDebugFactory(hiltWorkerFactoryProvider, featuresWorkerFactoryProvider);
  }

  public static WorkerFactory providesApplicationWorkerFactory$app_freeDebug(
      HiltWorkerFactory hiltWorkerFactory, FeaturesWorkerFactory featuresWorkerFactory) {
    return Preconditions.checkNotNullFromProvides(BackgroundJobsModule.Companion.providesApplicationWorkerFactory$app_freeDebug(hiltWorkerFactory, featuresWorkerFactory));
  }
}
