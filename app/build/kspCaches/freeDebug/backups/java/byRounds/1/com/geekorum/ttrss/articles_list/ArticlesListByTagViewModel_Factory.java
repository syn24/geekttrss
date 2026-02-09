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
public final class ArticlesListByTagViewModel_Factory {
  private final Provider<BackgroundJobManager> backgroundJobManagerProvider;

  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private ArticlesListByTagViewModel_Factory(
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.backgroundJobManagerProvider = backgroundJobManagerProvider;
    this.componentFactoryProvider = componentFactoryProvider;
  }

  public ArticlesListByTagViewModel get(String tag) {
    return newInstance(tag, backgroundJobManagerProvider.get(), componentFactoryProvider.get());
  }

  public static ArticlesListByTagViewModel_Factory create(
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new ArticlesListByTagViewModel_Factory(backgroundJobManagerProvider, componentFactoryProvider);
  }

  public static ArticlesListByTagViewModel newInstance(String tag,
      BackgroundJobManager backgroundJobManager,
      SessionActivityComponent.Factory componentFactory) {
    return new ArticlesListByTagViewModel(tag, backgroundJobManager, componentFactory);
  }
}
