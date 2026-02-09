package com.geekorum.ttrss.in_app_update;

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
public final class InAppUpdateViewModel_Factory implements Factory<InAppUpdateViewModel> {
  private final Provider<InAppUpdateManager> updateManagerProvider;

  private InAppUpdateViewModel_Factory(Provider<InAppUpdateManager> updateManagerProvider) {
    this.updateManagerProvider = updateManagerProvider;
  }

  @Override
  public InAppUpdateViewModel get() {
    return newInstance(updateManagerProvider.get());
  }

  public static InAppUpdateViewModel_Factory create(
      Provider<InAppUpdateManager> updateManagerProvider) {
    return new InAppUpdateViewModel_Factory(updateManagerProvider);
  }

  public static InAppUpdateViewModel newInstance(InAppUpdateManager updateManager) {
    return new InAppUpdateViewModel(updateManager);
  }
}
