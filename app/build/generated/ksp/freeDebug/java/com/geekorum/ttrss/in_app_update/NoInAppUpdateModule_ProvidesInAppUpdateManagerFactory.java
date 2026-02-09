package com.geekorum.ttrss.in_app_update;

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
public final class NoInAppUpdateModule_ProvidesInAppUpdateManagerFactory implements Factory<InAppUpdateManager> {
  @Override
  public InAppUpdateManager get() {
    return providesInAppUpdateManager();
  }

  public static NoInAppUpdateModule_ProvidesInAppUpdateManagerFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InAppUpdateManager providesInAppUpdateManager() {
    return Preconditions.checkNotNullFromProvides(NoInAppUpdateModule.INSTANCE.providesInAppUpdateManager());
  }

  private static final class InstanceHolder {
    static final NoInAppUpdateModule_ProvidesInAppUpdateManagerFactory INSTANCE = new NoInAppUpdateModule_ProvidesInAppUpdateManagerFactory();
  }
}
