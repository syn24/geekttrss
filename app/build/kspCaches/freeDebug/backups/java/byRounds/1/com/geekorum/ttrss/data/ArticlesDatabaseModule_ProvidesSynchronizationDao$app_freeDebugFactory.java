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
public final class ArticlesDatabaseModule_ProvidesSynchronizationDao$app_freeDebugFactory implements Factory<SynchronizationDao> {
  private final Provider<ArticlesDatabase> databaseProvider;

  private ArticlesDatabaseModule_ProvidesSynchronizationDao$app_freeDebugFactory(
      Provider<ArticlesDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SynchronizationDao get() {
    return providesSynchronizationDao$app_freeDebug(databaseProvider.get());
  }

  public static ArticlesDatabaseModule_ProvidesSynchronizationDao$app_freeDebugFactory create(
      Provider<ArticlesDatabase> databaseProvider) {
    return new ArticlesDatabaseModule_ProvidesSynchronizationDao$app_freeDebugFactory(databaseProvider);
  }

  public static SynchronizationDao providesSynchronizationDao$app_freeDebug(
      ArticlesDatabase database) {
    return Preconditions.checkNotNullFromProvides(ArticlesDatabaseModule.INSTANCE.providesSynchronizationDao$app_freeDebug(database));
  }
}
