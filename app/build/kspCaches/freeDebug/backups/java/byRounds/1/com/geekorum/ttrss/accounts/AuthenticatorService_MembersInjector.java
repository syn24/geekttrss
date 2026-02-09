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
public final class AuthenticatorService_MembersInjector implements MembersInjector<AuthenticatorService> {
  private final Provider<AccountAuthenticator> accountAuthenticatorProvider;

  private AuthenticatorService_MembersInjector(
      Provider<AccountAuthenticator> accountAuthenticatorProvider) {
    this.accountAuthenticatorProvider = accountAuthenticatorProvider;
  }

  @Override
  public void injectMembers(AuthenticatorService instance) {
    injectAccountAuthenticator(instance, accountAuthenticatorProvider.get());
  }

  public static MembersInjector<AuthenticatorService> create(
      Provider<AccountAuthenticator> accountAuthenticatorProvider) {
    return new AuthenticatorService_MembersInjector(accountAuthenticatorProvider);
  }

  @InjectedFieldSignature("com.geekorum.ttrss.accounts.AuthenticatorService.accountAuthenticator")
  public static void injectAccountAuthenticator(AuthenticatorService instance,
      AccountAuthenticator accountAuthenticator) {
    instance.accountAuthenticator = accountAuthenticator;
  }
}
