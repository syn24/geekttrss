package com.geekorum.ttrss.accounts;

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
public final class AndroidTinyrssAccountManagerModule_ProvidesTinyrssAccountManagerFactory implements Factory<TinyrssAccountManager> {
  private final Provider<AndroidTinyrssAccountManager> androidTinyrssAccountManagerProvider;

  private AndroidTinyrssAccountManagerModule_ProvidesTinyrssAccountManagerFactory(
      Provider<AndroidTinyrssAccountManager> androidTinyrssAccountManagerProvider) {
    this.androidTinyrssAccountManagerProvider = androidTinyrssAccountManagerProvider;
  }

  @Override
  public TinyrssAccountManager get() {
    return providesTinyrssAccountManager(androidTinyrssAccountManagerProvider.get());
  }

  public static AndroidTinyrssAccountManagerModule_ProvidesTinyrssAccountManagerFactory create(
      Provider<AndroidTinyrssAccountManager> androidTinyrssAccountManagerProvider) {
    return new AndroidTinyrssAccountManagerModule_ProvidesTinyrssAccountManagerFactory(androidTinyrssAccountManagerProvider);
  }

  public static TinyrssAccountManager providesTinyrssAccountManager(
      AndroidTinyrssAccountManager androidTinyrssAccountManager) {
    return Preconditions.checkNotNullFromProvides(AndroidTinyrssAccountManagerModule.INSTANCE.providesTinyrssAccountManager(androidTinyrssAccountManager));
  }
}
