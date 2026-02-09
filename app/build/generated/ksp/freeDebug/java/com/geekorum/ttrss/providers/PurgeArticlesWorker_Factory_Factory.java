package com.geekorum.ttrss.providers;

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
public final class PurgeArticlesWorker_Factory_Factory implements Factory<PurgeArticlesWorker.Factory> {
  private final Provider<PurgeArticlesDao> purgeArticlesDaoProvider;

  private PurgeArticlesWorker_Factory_Factory(Provider<PurgeArticlesDao> purgeArticlesDaoProvider) {
    this.purgeArticlesDaoProvider = purgeArticlesDaoProvider;
  }

  @Override
  public PurgeArticlesWorker.Factory get() {
    return newInstance(purgeArticlesDaoProvider.get());
  }

  public static PurgeArticlesWorker_Factory_Factory create(
      Provider<PurgeArticlesDao> purgeArticlesDaoProvider) {
    return new PurgeArticlesWorker_Factory_Factory(purgeArticlesDaoProvider);
  }

  public static PurgeArticlesWorker.Factory newInstance(PurgeArticlesDao purgeArticlesDao) {
    return new PurgeArticlesWorker.Factory(purgeArticlesDao);
  }
}
