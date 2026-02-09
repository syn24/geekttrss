package com.geekorum.ttrss.articles_list;

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
public final class ArticlesListViewModel_Factory_Impl implements ArticlesListViewModel.Factory {
  private final ArticlesListViewModel_Factory delegateFactory;

  ArticlesListViewModel_Factory_Impl(ArticlesListViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public ArticlesListViewModel create(long feedId) {
    return delegateFactory.get(feedId);
  }

  public static Provider<ArticlesListViewModel.Factory> create(
      ArticlesListViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticlesListViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<ArticlesListViewModel.Factory> createFactoryProvider(
      ArticlesListViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticlesListViewModel_Factory_Impl(delegateFactory));
  }
}
