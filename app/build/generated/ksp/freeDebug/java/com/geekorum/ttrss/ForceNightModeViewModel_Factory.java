package com.geekorum.ttrss;

import android.app.Application;
import android.os.PowerManager;
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
public final class ForceNightModeViewModel_Factory implements Factory<ForceNightModeViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<PowerManager> powerManagerProvider;

  private ForceNightModeViewModel_Factory(Provider<Application> applicationProvider,
      Provider<PowerManager> powerManagerProvider) {
    this.applicationProvider = applicationProvider;
    this.powerManagerProvider = powerManagerProvider;
  }

  @Override
  public ForceNightModeViewModel get() {
    return newInstance(applicationProvider.get(), powerManagerProvider.get());
  }

  public static ForceNightModeViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<PowerManager> powerManagerProvider) {
    return new ForceNightModeViewModel_Factory(applicationProvider, powerManagerProvider);
  }

  public static ForceNightModeViewModel newInstance(Application application,
      PowerManager powerManager) {
    return new ForceNightModeViewModel(application, powerManager);
  }
}
