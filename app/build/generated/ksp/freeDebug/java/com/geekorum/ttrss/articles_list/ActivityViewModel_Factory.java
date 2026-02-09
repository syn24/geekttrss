package com.geekorum.ttrss.articles_list;

import com.geekorum.ttrss.background_job.BackgroundJobManager;
import com.geekorum.ttrss.data.ArticlesSearchHistoryRepository;
import com.geekorum.ttrss.network.TtRssBrowserLauncher;
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
public final class ActivityViewModel_Factory implements Factory<ActivityViewModel> {
  private final Provider<TtRssBrowserLauncher> browserLauncherProvider;

  private final Provider<ArticlesListPreferencesRepository> articlesListPreferencesRepositoryProvider;

  private final Provider<ArticlesSearchHistoryRepository> articlesSearchHistoryRepositoryProvider;

  private final Provider<BackgroundJobManager> backgroundJobManagerProvider;

  private ActivityViewModel_Factory(Provider<TtRssBrowserLauncher> browserLauncherProvider,
      Provider<ArticlesListPreferencesRepository> articlesListPreferencesRepositoryProvider,
      Provider<ArticlesSearchHistoryRepository> articlesSearchHistoryRepositoryProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider) {
    this.browserLauncherProvider = browserLauncherProvider;
    this.articlesListPreferencesRepositoryProvider = articlesListPreferencesRepositoryProvider;
    this.articlesSearchHistoryRepositoryProvider = articlesSearchHistoryRepositoryProvider;
    this.backgroundJobManagerProvider = backgroundJobManagerProvider;
  }

  @Override
  public ActivityViewModel get() {
    return newInstance(browserLauncherProvider.get(), articlesListPreferencesRepositoryProvider.get(), articlesSearchHistoryRepositoryProvider.get(), backgroundJobManagerProvider.get());
  }

  public static ActivityViewModel_Factory create(
      Provider<TtRssBrowserLauncher> browserLauncherProvider,
      Provider<ArticlesListPreferencesRepository> articlesListPreferencesRepositoryProvider,
      Provider<ArticlesSearchHistoryRepository> articlesSearchHistoryRepositoryProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider) {
    return new ActivityViewModel_Factory(browserLauncherProvider, articlesListPreferencesRepositoryProvider, articlesSearchHistoryRepositoryProvider, backgroundJobManagerProvider);
  }

  public static ActivityViewModel newInstance(TtRssBrowserLauncher browserLauncher,
      ArticlesListPreferencesRepository articlesListPreferencesRepository,
      ArticlesSearchHistoryRepository articlesSearchHistoryRepository,
      BackgroundJobManager backgroundJobManager) {
    return new ActivityViewModel(browserLauncher, articlesListPreferencesRepository, articlesSearchHistoryRepository, backgroundJobManager);
  }
}
