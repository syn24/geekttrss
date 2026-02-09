package com.geekorum.ttrss;

import androidx.work.Configuration;
import coil.ImageLoader;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class Application_MembersInjector implements MembersInjector<Application> {
  private final Provider<Configuration> workManagerConfigProvider;

  private final Provider<ImageLoader> imageLoaderProvider;

  private Application_MembersInjector(Provider<Configuration> workManagerConfigProvider,
      Provider<ImageLoader> imageLoaderProvider) {
    this.workManagerConfigProvider = workManagerConfigProvider;
    this.imageLoaderProvider = imageLoaderProvider;
  }

  @Override
  public void injectMembers(Application instance) {
    injectWorkManagerConfig(instance, workManagerConfigProvider.get());
    injectImageLoader(instance, imageLoaderProvider.get());
  }

  public static MembersInjector<Application> create(
      Provider<Configuration> workManagerConfigProvider,
      Provider<ImageLoader> imageLoaderProvider) {
    return new Application_MembersInjector(workManagerConfigProvider, imageLoaderProvider);
  }

  @InjectedFieldSignature("com.geekorum.ttrss.Application.workManagerConfig")
  public static void injectWorkManagerConfig(Application instance,
      Configuration workManagerConfig) {
    instance.workManagerConfig = workManagerConfig;
  }

  @InjectedFieldSignature("com.geekorum.ttrss.Application.imageLoader")
  public static void injectImageLoader(Application instance, ImageLoader imageLoader) {
    instance.imageLoader = imageLoader;
  }
}
