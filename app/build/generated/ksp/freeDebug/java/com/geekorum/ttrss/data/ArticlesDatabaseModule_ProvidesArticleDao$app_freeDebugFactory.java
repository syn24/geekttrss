package com.geekorum.ttrss.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ArticlesDatabaseModule_ProvidesArticleDao$app_freeDebugFactory implements Factory<ArticleDao> {
  private final Provider<ArticlesDatabase> databaseProvider;

  private ArticlesDatabaseModule_ProvidesArticleDao$app_freeDebugFactory(
      Provider<ArticlesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ArticleDao get() {
    return providesArticleDao$app_freeDebug(databaseProvider.get());
  }

  public static ArticlesDatabaseModule_ProvidesArticleDao$app_freeDebugFactory create(
      Provider<ArticlesDatabase> databaseProvider) {
    return new ArticlesDatabaseModule_ProvidesArticleDao$app_freeDebugFactory(databaseProvider);
  }

  public static ArticleDao providesArticleDao$app_freeDebug(ArticlesDatabase database) {
    return Preconditions.checkNotNullFromProvides(ArticlesDatabaseModule.INSTANCE.providesArticleDao$app_freeDebug(database));
  }
}
