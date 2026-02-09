package com.geekorum.ttrss;

import com.geekorum.ttrss.on_demand_modules.OnDemandModuleManager;
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
public final class AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory implements Factory<OnDemandModuleManager> {
  @Override
  public OnDemandModuleManager get() {
    return providesOnDemandModuleManager();
  }

  public static AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OnDemandModuleManager providesOnDemandModuleManager() {
    return Preconditions.checkNotNullFromProvides(AllFeaturesInstalledModule.INSTANCE.providesOnDemandModuleManager());
  }

  private static final class InstanceHolder {
    static final AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory INSTANCE = new AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory();
  }
}
