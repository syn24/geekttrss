package com.geekorum.ttrss.sync;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ArticleSyncAdapter_Factory implements Factory<ArticleSyncAdapter> {
  private final Provider<Context> contextProvider;

  private final Provider<SyncComponent.Builder> syncBuilderProvider;

  private ArticleSyncAdapter_Factory(Provider<Context> contextProvider,
      Provider<SyncComponent.Builder> syncBuilderProvider) {
    this.contextProvider = contextProvider;
    this.syncBuilderProvider = syncBuilderProvider;
  }

  @Override
  public ArticleSyncAdapter get() {
    return newInstance(contextProvider.get(), syncBuilderProvider.get());
  }

  public static ArticleSyncAdapter_Factory create(Provider<Context> contextProvider,
      Provider<SyncComponent.Builder> syncBuilderProvider) {
    return new ArticleSyncAdapter_Factory(contextProvider, syncBuilderProvider);
  }

  public static ArticleSyncAdapter newInstance(Context context, SyncComponent.Builder syncBuilder) {
    return new ArticleSyncAdapter(context, syncBuilder);
  }
}
