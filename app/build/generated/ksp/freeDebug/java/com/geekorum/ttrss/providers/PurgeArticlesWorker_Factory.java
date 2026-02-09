package com.geekorum.ttrss.providers;

import android.content.Context;
import androidx.work.WorkerParameters;
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
public final class PurgeArticlesWorker_Factory {
  private final Provider<PurgeArticlesDao> purgeArticlesDaoProvider;

  private PurgeArticlesWorker_Factory(Provider<PurgeArticlesDao> purgeArticlesDaoProvider) {
    this.purgeArticlesDaoProvider = purgeArticlesDaoProvider;
  }

  public PurgeArticlesWorker get(Context appContext, WorkerParameters params) {
    return newInstance(appContext, params, purgeArticlesDaoProvider.get());
  }

  public static PurgeArticlesWorker_Factory create(
      Provider<PurgeArticlesDao> purgeArticlesDaoProvider) {
    return new PurgeArticlesWorker_Factory(purgeArticlesDaoProvider);
  }

  public static PurgeArticlesWorker newInstance(Context appContext, WorkerParameters params,
      PurgeArticlesDao purgeArticlesDao) {
    return new PurgeArticlesWorker(appContext, params, purgeArticlesDao);
  }
}
