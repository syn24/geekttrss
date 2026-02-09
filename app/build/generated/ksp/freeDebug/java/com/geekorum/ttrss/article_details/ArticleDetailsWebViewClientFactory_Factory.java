package com.geekorum.ttrss.article_details;

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
public final class ArticleDetailsWebViewClientFactory_Factory implements Factory<ArticleDetailsWebViewClientFactory> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<WebFontProvider> webFontProvider;

  private ArticleDetailsWebViewClientFactory_Factory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<WebFontProvider> webFontProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.webFontProvider = webFontProvider;
  }

  @Override
  public ArticleDetailsWebViewClientFactory get() {
    return newInstance(okHttpClientProvider.get(), webFontProvider.get());
  }

  public static ArticleDetailsWebViewClientFactory_Factory create(
      Provider<OkHttpClient> okHttpClientProvider, Provider<WebFontProvider> webFontProvider) {
    return new ArticleDetailsWebViewClientFactory_Factory(okHttpClientProvider, webFontProvider);
  }

  public static ArticleDetailsWebViewClientFactory newInstance(OkHttpClient okHttpClient,
      WebFontProvider webFontProvider) {
    return new ArticleDetailsWebViewClientFactory(okHttpClient, webFontProvider);
  }
}
