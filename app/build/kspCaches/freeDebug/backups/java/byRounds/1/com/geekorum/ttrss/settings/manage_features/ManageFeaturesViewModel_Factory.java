package com.geekorum.ttrss.settings.manage_features;

import com.geekorum.ttrss.on_demand_modules.OnDemandModuleManager;
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
public final class ManageFeaturesViewModel_Factory implements Factory<ManageFeaturesViewModel> {
  private final Provider<OnDemandModuleManager> moduleManagerProvider;

  private ManageFeaturesViewModel_Factory(Provider<OnDemandModuleManager> moduleManagerProvider) {
    this.moduleManagerProvider = moduleManagerProvider;
  }

  @Override
  public ManageFeaturesViewModel get() {
    return newInstance(moduleManagerProvider.get());
  }

  public static ManageFeaturesViewModel_Factory create(
      Provider<OnDemandModuleManager> moduleManagerProvider) {
    return new ManageFeaturesViewModel_Factory(moduleManagerProvider);
  }

  public static ManageFeaturesViewModel newInstance(OnDemandModuleManager moduleManager) {
    return new ManageFeaturesViewModel(moduleManager);
  }
}
