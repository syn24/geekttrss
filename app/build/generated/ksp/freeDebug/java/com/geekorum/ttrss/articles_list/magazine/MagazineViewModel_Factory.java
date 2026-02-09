package com.geekorum.ttrss.articles_list.magazine;

import com.geekorum.ttrss.articles_list.FeedsRepository;
import com.geekorum.ttrss.background_job.BackgroundJobManager;
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
public final class MagazineViewModel_Factory implements Factory<MagazineViewModel> {
  private final Provider<BackgroundJobManager> backgroundJobManagerProvider;

  private final Provider<FeedsRepository> feedsRepositoryProvider;

  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private MagazineViewModel_Factory(Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<FeedsRepository> feedsRepositoryProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.backgroundJobManagerProvider = backgroundJobManagerProvider;
    this.feedsRepositoryProvider = feedsRepositoryProvider;
    this.componentFactoryProvider = componentFactoryProvider;
  }

  @Override
  public MagazineViewModel get() {
    return newInstance(backgroundJobManagerProvider.get(), feedsRepositoryProvider.get(), componentFactoryProvider.get());
  }

  public static MagazineViewModel_Factory create(
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<FeedsRepository> feedsRepositoryProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new MagazineViewModel_Factory(backgroundJobManagerProvider, feedsRepositoryProvider, componentFactoryProvider);
  }

  public static MagazineViewModel newInstance(BackgroundJobManager backgroundJobManager,
      FeedsRepository feedsRepository, SessionActivityComponent.Factory componentFactory) {
    return new MagazineViewModel(backgroundJobManager, feedsRepository, componentFactory);
  }
}
