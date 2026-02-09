package com.geekorum.ttrss.articles_list;

import android.accounts.AccountManager;
import com.geekorum.geekdroid.accounts.AccountSelector;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class TtrssAccountViewModel_Factory implements Factory<TtrssAccountViewModel> {
  private final Provider<AccountManager> accountManagerProvider;

  private final Provider<AccountSelector> accountSelectorProvider;

  private TtrssAccountViewModel_Factory(Provider<AccountManager> accountManagerProvider,
      Provider<AccountSelector> accountSelectorProvider) {
    this.accountManagerProvider = accountManagerProvider;
    this.accountSelectorProvider = accountSelectorProvider;
  }

  @Override
  public TtrssAccountViewModel get() {
    return newInstance(accountManagerProvider.get(), accountSelectorProvider.get());
  }

  public static TtrssAccountViewModel_Factory create(
      Provider<AccountManager> accountManagerProvider,
      Provider<AccountSelector> accountSelectorProvider) {
    return new TtrssAccountViewModel_Factory(accountManagerProvider, accountSelectorProvider);
  }

  public static TtrssAccountViewModel newInstance(AccountManager accountManager,
      AccountSelector accountSelector) {
    return new TtrssAccountViewModel(accountManager, accountSelector);
  }
}
