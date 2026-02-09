package com.geekorum.ttrss.network;

import com.geekorum.ttrss.accounts.ServerInformation;
import com.geekorum.ttrss.webapi.LoggedRequestInterceptorFactory;
import com.geekorum.ttrss.webapi.TinyRssApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Optional;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class TinyrssApiModule_Companion_ProvidesTinyRssApiFactory implements Factory<TinyRssApi> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<ServerInformation> serverInformationProvider;

  private final Provider<Optional<LoggedRequestInterceptorFactory>> loggedRequestInterceptorFactoryProvider;

  private TinyrssApiModule_Companion_ProvidesTinyRssApiFactory(
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<ServerInformation> serverInformationProvider,
      Provider<Optional<LoggedRequestInterceptorFactory>> loggedRequestInterceptorFactoryProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.serverInformationProvider = serverInformationProvider;
    this.loggedRequestInterceptorFactoryProvider = loggedRequestInterceptorFactoryProvider;
  }

  @Override
  public TinyRssApi get() {
    return providesTinyRssApi(okHttpClientProvider.get(), serverInformationProvider.get(), loggedRequestInterceptorFactoryProvider.get());
  }

  public static TinyrssApiModule_Companion_ProvidesTinyRssApiFactory create(
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<ServerInformation> serverInformationProvider,
      Provider<Optional<LoggedRequestInterceptorFactory>> loggedRequestInterceptorFactoryProvider) {
    return new TinyrssApiModule_Companion_ProvidesTinyRssApiFactory(okHttpClientProvider, serverInformationProvider, loggedRequestInterceptorFactoryProvider);
  }

  public static TinyRssApi providesTinyRssApi(OkHttpClient okHttpClient,
      ServerInformation serverInformation,
      Optional<LoggedRequestInterceptorFactory> loggedRequestInterceptorFactory) {
    return Preconditions.checkNotNullFromProvides(TinyrssApiModule.Companion.providesTinyRssApi(okHttpClient, serverInformation, loggedRequestInterceptorFactory));
  }
}
