package com.geekorum.ttrss.article_details;

import android.app.Application;
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
public final class ResourcesWebFontProvider_Factory implements Factory<ResourcesWebFontProvider> {
  private final Provider<Application> applicationProvider;

  private ResourcesWebFontProvider_Factory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public ResourcesWebFontProvider get() {
    return newInstance(applicationProvider.get());
  }

  public static ResourcesWebFontProvider_Factory create(Provider<Application> applicationProvider) {
    return new ResourcesWebFontProvider_Factory(applicationProvider);
  }

  public static ResourcesWebFontProvider newInstance(Application application) {
    return new ResourcesWebFontProvider(application);
  }
}
