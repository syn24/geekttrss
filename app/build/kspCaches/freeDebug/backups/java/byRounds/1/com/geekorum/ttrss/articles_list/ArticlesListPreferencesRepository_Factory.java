package com.geekorum.ttrss.articles_list;

import android.content.SharedPreferences;
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
public final class ArticlesListPreferencesRepository_Factory implements Factory<ArticlesListPreferencesRepository> {
  private final Provider<SharedPreferences> prefsProvider;

  private ArticlesListPreferencesRepository_Factory(Provider<SharedPreferences> prefsProvider) {
    this.prefsProvider = prefsProvider;
  }

  @Override
  public ArticlesListPreferencesRepository get() {
    return newInstance(prefsProvider.get());
  }

  public static ArticlesListPreferencesRepository_Factory create(
      Provider<SharedPreferences> prefsProvider) {
    return new ArticlesListPreferencesRepository_Factory(prefsProvider);
  }

  public static ArticlesListPreferencesRepository newInstance(SharedPreferences prefs) {
    return new ArticlesListPreferencesRepository(prefs);
  }
}
