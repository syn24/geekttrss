package com.geekorum.ttrss.articles_list;

import com.geekorum.ttrss.data.ArticleDao;
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
public final class ArticlesRepository_Factory implements Factory<ArticlesRepository> {
  private final Provider<ArticleDao> articleDaoProvider;

  private ArticlesRepository_Factory(Provider<ArticleDao> articleDaoProvider) {
    this.articleDaoProvider = articleDaoProvider;
  }

  @Override
  public ArticlesRepository get() {
    return newInstance(articleDaoProvider.get());
  }

  public static ArticlesRepository_Factory create(Provider<ArticleDao> articleDaoProvider) {
    return new ArticlesRepository_Factory(articleDaoProvider);
  }

  public static ArticlesRepository newInstance(ArticleDao articleDao) {
    return new ArticlesRepository(articleDao);
  }
}
