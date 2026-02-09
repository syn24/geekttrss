package com.geekorum.ttrss.session;

import android.accounts.Account;
import com.geekorum.geekdroid.accounts.AccountSelector;
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
public final class SessionAccountModule_ProvidesAccountFactory implements Factory<Account> {
  private final SessionAccountModule module;

  private final Provider<AccountSelector> accountSelectorProvider;

  private SessionAccountModule_ProvidesAccountFactory(SessionAccountModule module,
      Provider<AccountSelector> accountSelectorProvider) {
    this.module = module;
    this.accountSelectorProvider = accountSelectorProvider;
  }

  @Override
  public Account get() {
    return providesAccount(module, accountSelectorProvider.get());
  }

  public static SessionAccountModule_ProvidesAccountFactory create(SessionAccountModule module,
      Provider<AccountSelector> accountSelectorProvider) {
    return new SessionAccountModule_ProvidesAccountFactory(module, accountSelectorProvider);
  }

  public static Account providesAccount(SessionAccountModule instance,
      AccountSelector accountSelector) {
    return Preconditions.checkNotNullFromProvides(instance.providesAccount(accountSelector));
  }
}
