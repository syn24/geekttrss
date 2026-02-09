package com.geekorum.ttrss.background_job;

import android.app.Application;
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
public final class BackgroundJobManager_Factory implements Factory<BackgroundJobManager> {
  private final Provider<Application> applicationProvider;

  private BackgroundJobManager_Factory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public BackgroundJobManager get() {
    return newInstance(applicationProvider.get());
  }

  public static BackgroundJobManager_Factory create(Provider<Application> applicationProvider) {
    return new BackgroundJobManager_Factory(applicationProvider);
  }

  public static BackgroundJobManager newInstance(Application application) {
    return new BackgroundJobManager(application);
  }
}
