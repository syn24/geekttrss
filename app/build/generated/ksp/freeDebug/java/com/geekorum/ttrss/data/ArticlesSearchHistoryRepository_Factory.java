package com.geekorum.ttrss.data;

import androidx.datastore.core.DataStore;
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
public final class ArticlesSearchHistoryRepository_Factory implements Factory<ArticlesSearchHistoryRepository> {
  private final Provider<DataStore<ArticlesSearchHistory>> dataStoreProvider;

  private ArticlesSearchHistoryRepository_Factory(
      Provider<DataStore<ArticlesSearchHistory>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public ArticlesSearchHistoryRepository get() {
    return newInstance(dataStoreProvider.get());
  }

  public static ArticlesSearchHistoryRepository_Factory create(
      Provider<DataStore<ArticlesSearchHistory>> dataStoreProvider) {
    return new ArticlesSearchHistoryRepository_Factory(dataStoreProvider);
  }

  public static ArticlesSearchHistoryRepository newInstance(
      DataStore<ArticlesSearchHistory> dataStore) {
    return new ArticlesSearchHistoryRepository(dataStore);
  }
}
