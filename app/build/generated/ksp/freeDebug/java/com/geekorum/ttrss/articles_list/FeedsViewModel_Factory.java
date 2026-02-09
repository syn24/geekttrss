package com.geekorum.ttrss.articles_list;

import com.geekorum.ttrss.background_job.BackgroundJobManager;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import com.geekorum.ttrss.session.SessionActivityComponent;
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
public final class FeedsViewModel_Factory implements Factory<FeedsViewModel> {
  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<FeedsRepository> feedsRepositoryProvider;

  private final Provider<ArticlesRepository> articlesRepositoryProvider;

  private final Provider<BackgroundJobManager> backgroundJobManagerProvider;

  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private FeedsViewModel_Factory(Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<FeedsRepository> feedsRepositoryProvider,
      Provider<ArticlesRepository> articlesRepositoryProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.dispatchersProvider = dispatchersProvider;
    this.feedsRepositoryProvider = feedsRepositoryProvider;
    this.articlesRepositoryProvider = articlesRepositoryProvider;
    this.backgroundJobManagerProvider = backgroundJobManagerProvider;
    this.componentFactoryProvider = componentFactoryProvider;
  }

  @Override
  public FeedsViewModel get() {
    return newInstance(dispatchersProvider.get(), feedsRepositoryProvider.get(), articlesRepositoryProvider.get(), backgroundJobManagerProvider.get(), componentFactoryProvider.get());
  }

  public static FeedsViewModel_Factory create(
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<FeedsRepository> feedsRepositoryProvider,
      Provider<ArticlesRepository> articlesRepositoryProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new FeedsViewModel_Factory(dispatchersProvider, feedsRepositoryProvider, articlesRepositoryProvider, backgroundJobManagerProvider, componentFactoryProvider);
  }

  public static FeedsViewModel newInstance(CoroutineDispatchersProvider dispatchers,
      FeedsRepository feedsRepository, ArticlesRepository articlesRepository,
      BackgroundJobManager backgroundJobManager,
      SessionActivityComponent.Factory componentFactory) {
    return new FeedsViewModel(dispatchers, feedsRepository, articlesRepository, backgroundJobManager, componentFactory);
  }
}
