package com.geekorum.ttrss.sync;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class HttpCacher_Factory implements Factory<HttpCacher> {
  private final Provider<OkHttpClient> httpClientProvider;

  private HttpCacher_Factory(Provider<OkHttpClient> httpClientProvider) {
    this.httpClientProvider = httpClientProvider;
  }

  @Override
  public HttpCacher get() {
    return newInstance(httpClientProvider.get());
  }

  public static HttpCacher_Factory create(Provider<OkHttpClient> httpClientProvider) {
    return new HttpCacher_Factory(httpClientProvider);
  }

  public static HttpCacher newInstance(OkHttpClient httpClient) {
    return new HttpCacher(httpClient);
  }
}
