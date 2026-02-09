package com.geekorum.ttrss.network;

import com.geekorum.ttrss.accounts.ServerInformation;
import com.geekorum.ttrss.webapi.TinyRssApi;
import com.geekorum.ttrss.webapi.TokenRetriever;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class TinyrssApiModule_Companion_ProvideApiServiceFactory implements Factory<ApiService> {
  private final Provider<TokenRetriever> tokenRetrieverProvider;

  private final Provider<TinyRssApi> tinyRssApiProvider;

  private final Provider<ServerInformation> serverInformationProvider;

  private TinyrssApiModule_Companion_ProvideApiServiceFactory(
      Provider<TokenRetriever> tokenRetrieverProvider, Provider<TinyRssApi> tinyRssApiProvider,
      Provider<ServerInformation> serverInformationProvider) {
    this.tokenRetrieverProvider = tokenRetrieverProvider;
    this.tinyRssApiProvider = tinyRssApiProvider;
    this.serverInformationProvider = serverInformationProvider;
  }

  @Override
  public ApiService get() {
    return provideApiService(tokenRetrieverProvider.get(), tinyRssApiProvider.get(), serverInformationProvider.get());
  }

  public static TinyrssApiModule_Companion_ProvideApiServiceFactory create(
      Provider<TokenRetriever> tokenRetrieverProvider, Provider<TinyRssApi> tinyRssApiProvider,
      Provider<ServerInformation> serverInformationProvider) {
    return new TinyrssApiModule_Companion_ProvideApiServiceFactory(tokenRetrieverProvider, tinyRssApiProvider, serverInformationProvider);
  }

  public static ApiService provideApiService(TokenRetriever tokenRetriever, TinyRssApi tinyRssApi,
      ServerInformation serverInformation) {
    return Preconditions.checkNotNullFromProvides(TinyrssApiModule.Companion.provideApiService(tokenRetriever, tinyRssApi, serverInformation));
  }
}
