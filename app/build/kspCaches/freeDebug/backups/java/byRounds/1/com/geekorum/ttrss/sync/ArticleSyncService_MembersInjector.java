package com.geekorum.ttrss.sync;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class ArticleSyncService_MembersInjector implements MembersInjector<ArticleSyncService> {
  private final Provider<ArticleSyncAdapter> articleSyncAdapterProvider;

  private ArticleSyncService_MembersInjector(
      Provider<ArticleSyncAdapter> articleSyncAdapterProvider) {
    this.articleSyncAdapterProvider = articleSyncAdapterProvider;
  }

  @Override
  public void injectMembers(ArticleSyncService instance) {
    injectArticleSyncAdapter(instance, articleSyncAdapterProvider.get());
  }

  public static MembersInjector<ArticleSyncService> create(
      Provider<ArticleSyncAdapter> articleSyncAdapterProvider) {
    return new ArticleSyncService_MembersInjector(articleSyncAdapterProvider);
  }

  @InjectedFieldSignature("com.geekorum.ttrss.sync.ArticleSyncService.articleSyncAdapter")
  public static void injectArticleSyncAdapter(ArticleSyncService instance,
      ArticleSyncAdapter articleSyncAdapter) {
    instance.articleSyncAdapter = articleSyncAdapter;
  }
}
