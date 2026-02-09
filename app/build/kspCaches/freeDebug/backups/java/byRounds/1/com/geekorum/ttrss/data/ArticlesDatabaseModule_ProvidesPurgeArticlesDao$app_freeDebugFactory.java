package com.geekorum.ttrss.data;

import com.geekorum.ttrss.providers.PurgeArticlesDao;
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
public final class ArticlesDatabaseModule_ProvidesPurgeArticlesDao$app_freeDebugFactory implements Factory<PurgeArticlesDao> {
  private final Provider<ArticlesDatabase> databaseProvider;

  private ArticlesDatabaseModule_ProvidesPurgeArticlesDao$app_freeDebugFactory(
      Provider<ArticlesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public PurgeArticlesDao get() {
    return providesPurgeArticlesDao$app_freeDebug(databaseProvider.get());
  }

  public static ArticlesDatabaseModule_ProvidesPurgeArticlesDao$app_freeDebugFactory create(
      Provider<ArticlesDatabase> databaseProvider) {
    return new ArticlesDatabaseModule_ProvidesPurgeArticlesDao$app_freeDebugFactory(databaseProvider);
  }

  public static PurgeArticlesDao providesPurgeArticlesDao$app_freeDebug(ArticlesDatabase database) {
    return Preconditions.checkNotNullFromProvides(ArticlesDatabaseModule.INSTANCE.providesPurgeArticlesDao$app_freeDebug(database));
  }
}
