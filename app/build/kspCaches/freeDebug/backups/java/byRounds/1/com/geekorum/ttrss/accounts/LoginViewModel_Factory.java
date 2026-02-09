package com.geekorum.ttrss.accounts;

import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<TinyrssAccountManager> accountManagerProvider;

  private final Provider<AuthenticatorNetworkComponent.Builder> networkComponentBuilderProvider;

  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private LoginViewModel_Factory(Provider<TinyrssAccountManager> accountManagerProvider,
      Provider<AuthenticatorNetworkComponent.Builder> networkComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider) {
    this.accountManagerProvider = accountManagerProvider;
    this.networkComponentBuilderProvider = networkComponentBuilderProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(accountManagerProvider.get(), networkComponentBuilderProvider.get(), dispatchersProvider.get());
  }

  public static LoginViewModel_Factory create(
      Provider<TinyrssAccountManager> accountManagerProvider,
      Provider<AuthenticatorNetworkComponent.Builder> networkComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider) {
    return new LoginViewModel_Factory(accountManagerProvider, networkComponentBuilderProvider, dispatchersProvider);
  }

  public static LoginViewModel newInstance(TinyrssAccountManager accountManager,
      AuthenticatorNetworkComponent.Builder networkComponentBuilder,
      CoroutineDispatchersProvider dispatchers) {
    return new LoginViewModel(accountManager, networkComponentBuilder, dispatchers);
  }
}
