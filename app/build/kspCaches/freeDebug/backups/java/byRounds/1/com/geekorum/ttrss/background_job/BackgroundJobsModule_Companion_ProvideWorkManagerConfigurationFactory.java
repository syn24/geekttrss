package com.geekorum.ttrss.background_job;

import androidx.work.Configuration;
import androidx.work.WorkerFactory;
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
public final class BackgroundJobsModule_Companion_ProvideWorkManagerConfigurationFactory implements Factory<Configuration> {
  private final Provider<WorkerFactory> workerFactoryProvider;

  private BackgroundJobsModule_Companion_ProvideWorkManagerConfigurationFactory(
      Provider<WorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  @Override
  public Configuration get() {
    return provideWorkManagerConfiguration(workerFactoryProvider.get());
  }

  public static BackgroundJobsModule_Companion_ProvideWorkManagerConfigurationFactory create(
      Provider<WorkerFactory> workerFactoryProvider) {
    return new BackgroundJobsModule_Companion_ProvideWorkManagerConfigurationFactory(workerFactoryProvider);
  }

  public static Configuration provideWorkManagerConfiguration(WorkerFactory workerFactory) {
    return Preconditions.checkNotNullFromProvides(BackgroundJobsModule.Companion.provideWorkManagerConfiguration(workerFactory));
  }
}
