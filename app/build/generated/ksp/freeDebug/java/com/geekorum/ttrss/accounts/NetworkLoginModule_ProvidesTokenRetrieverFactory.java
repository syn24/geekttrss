package com.geekorum.ttrss.accounts;

import android.accounts.Account;
import android.accounts.AccountManager;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
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
public final class NetworkLoginModule_ProvidesTokenRetrieverFactory implements Factory<TokenRetriever> {
  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<AccountManager> accountManagerProvider;

  private final Provider<Account> accountProvider;

  private NetworkLoginModule_ProvidesTokenRetrieverFactory(
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<AccountManager> accountManagerProvider, Provider<Account> accountProvider) {
    this.dispatchersProvider = dispatchersProvider;
    this.accountManagerProvider = accountManagerProvider;
    this.accountProvider = accountProvider;
  }

  @Override
  public TokenRetriever get() {
    return providesTokenRetriever(dispatchersProvider.get(), accountManagerProvider.get(), accountProvider.get());
  }

  public static NetworkLoginModule_ProvidesTokenRetrieverFactory create(
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<AccountManager> accountManagerProvider, Provider<Account> accountProvider) {
    return new NetworkLoginModule_ProvidesTokenRetrieverFactory(dispatchersProvider, accountManagerProvider, accountProvider);
  }

  public static TokenRetriever providesTokenRetriever(CoroutineDispatchersProvider dispatchers,
      AccountManager accountManager, Account account) {
    return Preconditions.checkNotNullFromProvides(NetworkLoginModule.INSTANCE.providesTokenRetriever(dispatchers, accountManager, account));
  }
}
