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
public final class OnDemandModuleNavHostFragment_Factory implements Factory<OnDemandModuleNavHostFragment> {
  private final Provider<OnDemandModuleManager> onDemandModuleManagerProvider;

  private OnDemandModuleNavHostFragment_Factory(
      Provider<OnDemandModuleManager> onDemandModuleManagerProvider) {
    this.onDemandModuleManagerProvider = onDemandModuleManagerProvider;
  }

  @Override
  public OnDemandModuleNavHostFragment get() {
    return newInstance(onDemandModuleManagerProvider.get());
  }

  public static OnDemandModuleNavHostFragment_Factory create(
      Provider<OnDemandModuleManager> onDemandModuleManagerProvider) {
    return new OnDemandModuleNavHostFragment_Factory(onDemandModuleManagerProvider);
  }

  public static OnDemandModuleNavHostFragment newInstance(
      OnDemandModuleManager onDemandModuleManager) {
    return new OnDemandModuleNavHostFragment(onDemandModuleManager);
  }
}
