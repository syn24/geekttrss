package com.geekorum.ttrss.on_demand_modules;

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
public final class InstallModuleViewModel_Factory implements Factory<InstallModuleViewModel> {
  private final Provider<OnDemandModuleManager> moduleManagerProvider;

  private InstallModuleViewModel_Factory(Provider<OnDemandModuleManager> moduleManagerProvider) {
    this.moduleManagerProvider = moduleManagerProvider;
  }

  @Override
  public InstallModuleViewModel get() {
    return newInstance(moduleManagerProvider.get());
  }

  public static InstallModuleViewModel_Factory create(
      Provider<OnDemandModuleManager> moduleManagerProvider) {
    return new InstallModuleViewModel_Factory(moduleManagerProvider);
  }

  public static InstallModuleViewModel newInstance(OnDemandModuleManager moduleManager) {
    return new InstallModuleViewModel(moduleManager);
  }
}
