package com.geekorum.ttrss.session;

import android.accounts.AccountManager;
import android.app.Application;
import com.geekorum.geekdroid.accounts.AccountSelector;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AccountSelectorModule_BindsAccountSelectorFactory implements Factory<AccountSelector> {
  private final Provider<Application> applicationProvider;

  private final Provider<AccountManager> accountManagerProvider;

  private AccountSelectorModule_BindsAccountSelectorFactory(
      Provider<Application> applicationProvider, Provider<AccountManager> accountManagerProvider) {
    this.applicationProvider = applicationProvider;
    this.accountManagerProvider = accountManagerProvider;
  }

  @Override
  public AccountSelector get() {
    return bindsAccountSelector(applicationProvider.get(), accountManagerProvider.get());
  }

  public static AccountSelectorModule_BindsAccountSelectorFactory create(
      Provider<Application> applicationProvider, Provider<AccountManager> accountManagerProvider) {
    return new AccountSelectorModule_BindsAccountSelectorFactory(applicationProvider, accountManagerProvider);
  }

  public static AccountSelector bindsAccountSelector(Application application,
      AccountManager accountManager) {
    return Preconditions.checkNotNullFromProvides(AccountSelectorModule.INSTANCE.bindsAccountSelector(application, accountManager));
  }
}
