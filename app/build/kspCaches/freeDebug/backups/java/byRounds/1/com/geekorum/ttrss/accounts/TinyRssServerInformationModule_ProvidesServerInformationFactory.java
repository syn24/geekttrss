package com.geekorum.ttrss.accounts;

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
public final class TinyRssServerInformationModule_ProvidesServerInformationFactory implements Factory<ServerInformation> {
  private final TinyRssServerInformationModule module;

  private TinyRssServerInformationModule_ProvidesServerInformationFactory(
      TinyRssServerInformationModule module) {
    this.module = module;
  }

  @Override
  public ServerInformation get() {
    return providesServerInformation(module);
  }

  public static TinyRssServerInformationModule_ProvidesServerInformationFactory create(
      TinyRssServerInformationModule module) {
    return new TinyRssServerInformationModule_ProvidesServerInformationFactory(module);
  }

  public static ServerInformation providesServerInformation(
      TinyRssServerInformationModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.providesServerInformation());
  }
}
