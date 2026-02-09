package com.geekorum.ttrss.sync.workers;

import com.geekorum.favikonsnoop.FaviKonSnoop;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class FaviKonModule_ProvidesFaviKonSnoopFactory implements Factory<FaviKonSnoop> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<CoroutineDispatchersProvider> coroutineDispatchersProvider;

  private FaviKonModule_ProvidesFaviKonSnoopFactory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<CoroutineDispatchersProvider> coroutineDispatchersProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.coroutineDispatchersProvider = coroutineDispatchersProvider;
  }

  @Override
  public FaviKonSnoop get() {
    return providesFaviKonSnoop(okHttpClientProvider.get(), coroutineDispatchersProvider.get());
  }

  public static FaviKonModule_ProvidesFaviKonSnoopFactory create(
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<CoroutineDispatchersProvider> coroutineDispatchersProvider) {
    return new FaviKonModule_ProvidesFaviKonSnoopFactory(okHttpClientProvider, coroutineDispatchersProvider);
  }

  public static FaviKonSnoop providesFaviKonSnoop(OkHttpClient okHttpClient,
      CoroutineDispatchersProvider coroutineDispatchersProvider) {
    return Preconditions.checkNotNullFromProvides(FaviKonModule.INSTANCE.providesFaviKonSnoop(okHttpClient, coroutineDispatchersProvider));
  }
}
