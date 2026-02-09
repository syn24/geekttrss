package com.geekorum.ttrss.article_details;

import com.geekorum.ttrss.network.TtRssBrowserLauncher;
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
public final class ArticleDetailsViewModel_Factory {
  private final Provider<TtRssBrowserLauncher> browserLauncherProvider;

  private final Provider<SessionActivityComponent.Factory> componentFactoryProvider;

  private ArticleDetailsViewModel_Factory(Provider<TtRssBrowserLauncher> browserLauncherProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    this.browserLauncherProvider = browserLauncherProvider;
    this.componentFactoryProvider = componentFactoryProvider;
  }

  public ArticleDetailsViewModel get(long articleId) {
    return newInstance(articleId, browserLauncherProvider.get(), componentFactoryProvider.get());
  }

  public static ArticleDetailsViewModel_Factory create(
      Provider<TtRssBrowserLauncher> browserLauncherProvider,
      Provider<SessionActivityComponent.Factory> componentFactoryProvider) {
    return new ArticleDetailsViewModel_Factory(browserLauncherProvider, componentFactoryProvider);
  }

  public static ArticleDetailsViewModel newInstance(long articleId,
      TtRssBrowserLauncher browserLauncher, SessionActivityComponent.Factory componentFactory) {
    return new ArticleDetailsViewModel(articleId, browserLauncher, componentFactory);
  }
}
