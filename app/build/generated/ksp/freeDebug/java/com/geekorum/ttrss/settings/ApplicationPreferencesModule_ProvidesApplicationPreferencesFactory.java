package com.geekorum.ttrss.settings;

import android.app.Application;
import android.content.SharedPreferences;
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
public final class ApplicationPreferencesModule_ProvidesApplicationPreferencesFactory implements Factory<SharedPreferences> {
  private final Provider<Application> applicationProvider;

  private ApplicationPreferencesModule_ProvidesApplicationPreferencesFactory(
      Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public SharedPreferences get() {
    return providesApplicationPreferences(applicationProvider.get());
  }

  public static ApplicationPreferencesModule_ProvidesApplicationPreferencesFactory create(
      Provider<Application> applicationProvider) {
    return new ApplicationPreferencesModule_ProvidesApplicationPreferencesFactory(applicationProvider);
  }

  public static SharedPreferences providesApplicationPreferences(Application application) {
    return Preconditions.checkNotNullFromProvides(ApplicationPreferencesModule.INSTANCE.providesApplicationPreferences(application));
  }
}
