package com.geekorum.ttrss.accounts;

import android.accounts.Account;
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
public final class NetworkLoginModule_ProvidesServerInformationFactory implements Factory<ServerInformation> {
  private final Provider<AndroidTinyrssAccountManager> accountManagerProvider;

  private final Provider<Account> accountProvider;

  private NetworkLoginModule_ProvidesServerInformationFactory(
      Provider<AndroidTinyrssAccountManager> accountManagerProvider,
      Provider<Account> accountProvider) {
    this.accountManagerProvider = accountManagerProvider;
    this.accountProvider = accountProvider;
  }

  @Override
  public ServerInformation get() {
    return providesServerInformation(accountManagerProvider.get(), accountProvider.get());
  }

  public static NetworkLoginModule_ProvidesServerInformationFactory create(
      Provider<AndroidTinyrssAccountManager> accountManagerProvider,
      Provider<Account> accountProvider) {
    return new NetworkLoginModule_ProvidesServerInformationFactory(accountManagerProvider, accountProvider);
  }

  public static ServerInformation providesServerInformation(
      AndroidTinyrssAccountManager accountManager, Account account) {
    return Preconditions.checkNotNullFromProvides(NetworkLoginModule.INSTANCE.providesServerInformation(accountManager, account));
  }
}
