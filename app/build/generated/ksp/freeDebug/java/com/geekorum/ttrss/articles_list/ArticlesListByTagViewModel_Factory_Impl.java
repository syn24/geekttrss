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
public final class ArticlesListByTagViewModel_Factory_Impl implements ArticlesListByTagViewModel.Factory {
  private final ArticlesListByTagViewModel_Factory delegateFactory;

  ArticlesListByTagViewModel_Factory_Impl(ArticlesListByTagViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public ArticlesListByTagViewModel create(String tag) {
    return delegateFactory.get(tag);
  }

  public static Provider<ArticlesListByTagViewModel.Factory> create(
      ArticlesListByTagViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticlesListByTagViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<ArticlesListByTagViewModel.Factory> createFactoryProvider(
      ArticlesListByTagViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticlesListByTagViewModel_Factory_Impl(delegateFactory));
  }
}
