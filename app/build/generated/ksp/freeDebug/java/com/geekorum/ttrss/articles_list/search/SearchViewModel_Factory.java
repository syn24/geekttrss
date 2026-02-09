package com.geekorum.ttrss.articles_list.search;

import com.geekorum.ttrss.session.SessionActivityComponent;
import dagger.internal.DaggerGenerated;
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
public final class SearchViewModel_Factory {
  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private SearchViewModel_Factory(
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.componentFactoryProvider = componentFactoryProvider;
  }

  public SearchViewModel get(String query) {
    return newInstance(query, componentFactoryProvider.get());
  }

  public static SearchViewModel_Factory create(
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new SearchViewModel_Factory(componentFactoryProvider);
  }

  public static SearchViewModel newInstance(String query,
      SessionActivityComponent.Factory componentFactory) {
    return new SearchViewModel(query, componentFactory);
  }
}
