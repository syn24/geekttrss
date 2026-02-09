package com.geekorum.ttrss.accounts;

import com.geekorum.ttrss.webapi.LoggedRequestInterceptorFactory;
import com.geekorum.ttrss.webapi.TokenRetriever;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("com.geekorum.ttrss.accounts.PerAccount")
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
public final class NetworkLoginModule_ProvidesLoggedRequestInterceptorFactoryFactory implements Factory<LoggedRequestInterceptorFactory> {
  private final Provider<TokenRetriever> tokenRetrieverProvider;

  private NetworkLoginModule_ProvidesLoggedRequestInterceptorFactoryFactory(
      Provider<TokenRetriever> tokenRetrieverProvider) {
    this.tokenRetrieverProvider = tokenRetrieverProvider;
  }

  @Override
  public LoggedRequestInterceptorFactory get() {
    return providesLoggedRequestInterceptorFactory(tokenRetrieverProvider.get());
  }

  public static NetworkLoginModule_ProvidesLoggedRequestInterceptorFactoryFactory create(
      Provider<TokenRetriever> tokenRetrieverProvider) {
    return new NetworkLoginModule_ProvidesLoggedRequestInterceptorFactoryFactory(tokenRetrieverProvider);
  }

  public static LoggedRequestInterceptorFactory providesLoggedRequestInterceptorFactory(
      TokenRetriever tokenRetriever) {
    return Preconditions.checkNotNullFromProvides(NetworkLoginModule.INSTANCE.providesLoggedRequestInterceptorFactory(tokenRetriever));
  }
}
