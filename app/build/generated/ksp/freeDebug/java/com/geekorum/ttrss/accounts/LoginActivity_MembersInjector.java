package com.geekorum.ttrss.accounts;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class LoginActivity_MembersInjector implements MembersInjector<LoginActivity> {
  private final Provider<AndroidTinyrssAccountManager> accountManagerProvider;

  private LoginActivity_MembersInjector(
      Provider<AndroidTinyrssAccountManager> accountManagerProvider) {
    this.accountManagerProvider = accountManagerProvider;
  }

  @Override
  public void injectMembers(LoginActivity instance) {
    injectAccountManager(instance, accountManagerProvider.get());
  }

  public static MembersInjector<LoginActivity> create(
      Provider<AndroidTinyrssAccountManager> accountManagerProvider) {
    return new LoginActivity_MembersInjector(accountManagerProvider);
  }

  @InjectedFieldSignature("com.geekorum.ttrss.accounts.LoginActivity.accountManager")
  public static void injectAccountManager(LoginActivity instance,
      AndroidTinyrssAccountManager accountManager) {
    instance.accountManager = accountManager;
  }
}
