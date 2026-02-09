package com.geekorum.ttrss.accounts;

import android.accounts.AccountManager;
import com.geekorum.geekdroid.security.SecretEncryption;
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
public final class AndroidTinyrssAccountManagerModule_ProvidesAndroidTinyrssAccountManagerFactory implements Factory<AndroidTinyrssAccountManager> {
  private final Provider<AccountManager> accountManagerProvider;

  private final Provider<SecretEncryption> secretEncryptionProvider;

  private AndroidTinyrssAccountManagerModule_ProvidesAndroidTinyrssAccountManagerFactory(
      Provider<AccountManager> accountManagerProvider,
      Provider<SecretEncryption> secretEncryptionProvider) {
    this.accountManagerProvider = accountManagerProvider;
    this.secretEncryptionProvider = secretEncryptionProvider;
  }

  @Override
  public AndroidTinyrssAccountManager get() {
    return providesAndroidTinyrssAccountManager(accountManagerProvider.get(), secretEncryptionProvider.get());
  }

  public static AndroidTinyrssAccountManagerModule_ProvidesAndroidTinyrssAccountManagerFactory create(
      Provider<AccountManager> accountManagerProvider,
      Provider<SecretEncryption> secretEncryptionProvider) {
    return new AndroidTinyrssAccountManagerModule_ProvidesAndroidTinyrssAccountManagerFactory(accountManagerProvider, secretEncryptionProvider);
  }

  public static AndroidTinyrssAccountManager providesAndroidTinyrssAccountManager(
      AccountManager accountManager, SecretEncryption secretEncryption) {
    return Preconditions.checkNotNullFromProvides(AndroidTinyrssAccountManagerModule.INSTANCE.providesAndroidTinyrssAccountManager(accountManager, secretEncryption));
  }
}
