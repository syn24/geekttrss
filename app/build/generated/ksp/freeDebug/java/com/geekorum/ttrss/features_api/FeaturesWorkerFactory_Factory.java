package com.geekorum.ttrss.features_api;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class FeaturesWorkerFactory_Factory implements Factory<FeaturesWorkerFactory> {
  @Override
  public FeaturesWorkerFactory get() {
    return newInstance();
  }

  public static FeaturesWorkerFactory_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FeaturesWorkerFactory newInstance() {
    return new FeaturesWorkerFactory();
  }

  private static final class InstanceHolder {
    static final FeaturesWorkerFactory_Factory INSTANCE = new FeaturesWorkerFactory_Factory();
  }
}
