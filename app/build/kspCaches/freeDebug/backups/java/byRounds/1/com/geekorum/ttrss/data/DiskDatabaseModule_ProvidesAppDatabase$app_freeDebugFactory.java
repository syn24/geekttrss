package com.geekorum.ttrss.data;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DiskDatabaseModule_ProvidesAppDatabase$app_freeDebugFactory implements Factory<ArticlesDatabase> {
  private final Provider<Application> applicationProvider;

  private DiskDatabaseModule_ProvidesAppDatabase$app_freeDebugFactory(
      Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public ArticlesDatabase get() {
    return providesAppDatabase$app_freeDebug(applicationProvider.get());
  }

  public static DiskDatabaseModule_ProvidesAppDatabase$app_freeDebugFactory create(
      Provider<Application> applicationProvider) {
    return new DiskDatabaseModule_ProvidesAppDatabase$app_freeDebugFactory(applicationProvider);
  }

  public static ArticlesDatabase providesAppDatabase$app_freeDebug(Application application) {
    return Preconditions.checkNotNullFromProvides(DiskDatabaseModule.INSTANCE.providesAppDatabase$app_freeDebug(application));
  }
}
