package com.geekorum.ttrss.publish_article;

import com.geekorum.ttrss.session.SessionActivityComponent;
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
public final class SharingToPublishViewModel_Factory implements Factory<SharingToPublishViewModel> {
  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private SharingToPublishViewModel_Factory(
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.componentFactoryProvider = componentFactoryProvider;
  }

  @Override
  public SharingToPublishViewModel get() {
    return newInstance(componentFactoryProvider.get());
  }

  public static SharingToPublishViewModel_Factory create(
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new SharingToPublishViewModel_Factory(componentFactoryProvider);
  }

  public static SharingToPublishViewModel newInstance(
      SessionActivityComponent.Factory componentFactory) {
    return new SharingToPublishViewModel(componentFactory);
  }
}
