package com.geekorum.ttrss.articles_list;

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
public final class TagsViewModel_Factory implements Factory<TagsViewModel> {
  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private TagsViewModel_Factory(
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.componentFactoryProvider = componentFactoryProvider;
  }

  @Override
  public TagsViewModel get() {
    return newInstance(componentFactoryProvider.get());
  }

  public static TagsViewModel_Factory create(
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new TagsViewModel_Factory(componentFactoryProvider);
  }

  public static TagsViewModel newInstance(SessionActivityComponent.Factory componentFactory) {
    return new TagsViewModel(componentFactory);
  }
}
