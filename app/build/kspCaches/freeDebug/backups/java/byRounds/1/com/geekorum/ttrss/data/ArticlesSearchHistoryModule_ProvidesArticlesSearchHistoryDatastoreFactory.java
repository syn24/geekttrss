package com.geekorum.ttrss.data;

import android.content.Context;
import androidx.datastore.core.DataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ArticlesSearchHistoryModule_ProvidesArticlesSearchHistoryDatastoreFactory implements Factory<DataStore<ArticlesSearchHistory>> {
  private final Provider<Context> contextProvider;

  private ArticlesSearchHistoryModule_ProvidesArticlesSearchHistoryDatastoreFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DataStore<ArticlesSearchHistory> get() {
    return providesArticlesSearchHistoryDatastore(contextProvider.get());
  }

  public static ArticlesSearchHistoryModule_ProvidesArticlesSearchHistoryDatastoreFactory create(
      Provider<Context> contextProvider) {
    return new ArticlesSearchHistoryModule_ProvidesArticlesSearchHistoryDatastoreFactory(contextProvider);
  }

  public static DataStore<ArticlesSearchHistory> providesArticlesSearchHistoryDatastore(
      Context context) {
    return Preconditions.checkNotNullFromProvides(ArticlesSearchHistoryModule.INSTANCE.providesArticlesSearchHistoryDatastore(context));
  }
}
