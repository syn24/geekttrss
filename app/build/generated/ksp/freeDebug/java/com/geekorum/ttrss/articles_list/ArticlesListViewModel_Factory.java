package com.geekorum.ttrss.articles_list;

import com.geekorum.ttrss.background_job.BackgroundJobManager;
import com.geekorum.ttrss.session.SessionActivityComponent;
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
public final class ArticlesListViewModel_Factory {
  private final Provider<FeedsRepository> feedsRepositoryProvider;

  private final Provider<BackgroundJobManager> backgroundJobManagerProvider;

  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private ArticlesListViewModel_Factory(Provider<FeedsRepository> feedsRepositoryProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.feedsRepositoryProvider = feedsRepositoryProvider;
    this.backgroundJobManagerProvider = backgroundJobManagerProvider;
    this.componentFactoryProvider = componentFactoryProvider;
  }

  public ArticlesListViewModel get(long feedId) {
    return newInstance(feedId, feedsRepositoryProvider.get(), backgroundJobManagerProvider.get(), componentFactoryProvider.get());
  }

  public static ArticlesListViewModel_Factory create(
      Provider<FeedsRepository> feedsRepositoryProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new ArticlesListViewModel_Factory(feedsRepositoryProvider, backgroundJobManagerProvider, componentFactoryProvider);
  }

  public static ArticlesListViewModel newInstance(long feedId, FeedsRepository feedsRepository,
      BackgroundJobManager backgroundJobManager,
      SessionActivityComponent.Factory componentFactory) {
    return new ArticlesListViewModel(feedId, feedsRepository, backgroundJobManager, componentFactory);
  }
}
