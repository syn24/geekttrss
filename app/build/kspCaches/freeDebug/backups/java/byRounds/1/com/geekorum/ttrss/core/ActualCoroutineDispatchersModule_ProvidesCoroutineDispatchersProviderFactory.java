package com.geekorum.ttrss.core;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ActualCoroutineDispatchersModule_ProvidesCoroutineDispatchersProviderFactory implements Factory<CoroutineDispatchersProvider> {
  @Override
  public CoroutineDispatchersProvider get() {
    return providesCoroutineDispatchersProvider();
  }

  public static ActualCoroutineDispatchersModule_ProvidesCoroutineDispatchersProviderFactory create(
      ) {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineDispatchersProvider providesCoroutineDispatchersProvider() {
    return Preconditions.checkNotNullFromProvides(ActualCoroutineDispatchersModule.INSTANCE.providesCoroutineDispatchersProvider());
  }

  private static final class InstanceHolder {
    static final ActualCoroutineDispatchersModule_ProvidesCoroutineDispatchersProviderFactory INSTANCE = new ActualCoroutineDispatchersModule_ProvidesCoroutineDispatchersProviderFactory();
  }
}
