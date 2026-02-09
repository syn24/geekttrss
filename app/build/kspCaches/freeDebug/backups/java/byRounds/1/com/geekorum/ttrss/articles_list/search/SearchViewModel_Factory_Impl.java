package com.geekorum.ttrss.articles_list.search;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SearchViewModel_Factory_Impl implements SearchViewModel.Factory {
  private final SearchViewModel_Factory delegateFactory;

  SearchViewModel_Factory_Impl(SearchViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SearchViewModel create(String query) {
    return delegateFactory.get(query);
  }

  public static Provider<SearchViewModel.Factory> create(SearchViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new SearchViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SearchViewModel.Factory> createFactoryProvider(
      SearchViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new SearchViewModel_Factory_Impl(delegateFactory));
  }
}
