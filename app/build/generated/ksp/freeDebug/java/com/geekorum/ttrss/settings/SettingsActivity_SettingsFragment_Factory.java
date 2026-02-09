package com.geekorum.ttrss.settings;

import android.content.pm.PackageManager;
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
public final class SettingsActivity_SettingsFragment_Factory implements Factory<SettingsActivity.SettingsFragment> {
  private final Provider<PackageManager> packageManagerProvider;

  private SettingsActivity_SettingsFragment_Factory(
      Provider<PackageManager> packageManagerProvider) {
    this.packageManagerProvider = packageManagerProvider;
  }

  @Override
  public SettingsActivity.SettingsFragment get() {
    return newInstance(packageManagerProvider.get());
  }

  public static SettingsActivity_SettingsFragment_Factory create(
      Provider<PackageManager> packageManagerProvider) {
    return new SettingsActivity_SettingsFragment_Factory(packageManagerProvider);
  }

  public static SettingsActivity.SettingsFragment newInstance(PackageManager packageManager) {
    return new SettingsActivity.SettingsFragment(packageManager);
  }
}
