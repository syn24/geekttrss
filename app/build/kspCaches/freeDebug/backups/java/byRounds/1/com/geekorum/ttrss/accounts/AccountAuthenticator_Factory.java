package com.geekorum.ttrss.accounts;

import android.content.Context;
import com.geekorum.ttrss.background_job.BackgroundJobManager;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AccountAuthenticator_Factory implements Factory<AccountAuthenticator> {
  private final Provider<Context> contextProvider;

  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<AndroidTinyrssAccountManager> accountManagerProvider;

  private final Provider<BackgroundJobManager> backgroundJobManagerProvider;

  private final Provider<AuthenticatorNetworkComponent.Builder> authenticatorBuilderProvider;

  private AccountAuthenticator_Factory(Provider<Context> contextProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<AndroidTinyrssAccountManager> accountManagerProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<AuthenticatorNetworkComponent.Builder> authenticatorBuilderProvider) {
    this.contextProvider = contextProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.accountManagerProvider = accountManagerProvider;
    this.backgroundJobManagerProvider = backgroundJobManagerProvider;
    this.authenticatorBuilderProvider = authenticatorBuilderProvider;
  }

  @Override
  public AccountAuthenticator get() {
    return newInstance(contextProvider.get(), dispatchersProvider.get(), accountManagerProvider.get(), backgroundJobManagerProvider.get(), authenticatorBuilderProvider.get());
  }

  public static AccountAuthenticator_Factory create(Provider<Context> contextProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<AndroidTinyrssAccountManager> accountManagerProvider,
      Provider<BackgroundJobManager> backgroundJobManagerProvider,
      Provider<AuthenticatorNetworkComponent.Builder> authenticatorBuilderProvider) {
    return new AccountAuthenticator_Factory(contextProvider, dispatchersProvider, accountManagerProvider, backgroundJobManagerProvider, authenticatorBuilderProvider);
  }

  public static AccountAuthenticator newInstance(Context context,
      CoroutineDispatchersProvider dispatchers, AndroidTinyrssAccountManager accountManager,
      BackgroundJobManager backgroundJobManager,
      AuthenticatorNetworkComponent.Builder authenticatorBuilder) {
    return new AccountAuthenticator(context, dispatchers, accountManager, backgroundJobManager, authenticatorBuilder);
  }
}
