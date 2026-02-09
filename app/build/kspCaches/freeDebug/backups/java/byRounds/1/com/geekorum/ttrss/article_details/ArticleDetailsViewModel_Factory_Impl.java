package com.geekorum.ttrss.article_details;

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
public final class ArticleDetailsViewModel_Factory_Impl implements ArticleDetailsViewModel.Factory {
  private final ArticleDetailsViewModel_Factory delegateFactory;

  ArticleDetailsViewModel_Factory_Impl(ArticleDetailsViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public ArticleDetailsViewModel create(long articleId) {
    return delegateFactory.get(articleId);
  }

  public static Provider<ArticleDetailsViewModel.Factory> create(
      ArticleDetailsViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticleDetailsViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<ArticleDetailsViewModel.Factory> createFactoryProvider(
      ArticleDetailsViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new ArticleDetailsViewModel_Factory_Impl(delegateFactory));
  }
}
