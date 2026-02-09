package com.geekorum.ttrss.data.feedsettings;

import android.content.Context;
import androidx.datastore.core.DataStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class FeedsSettingsModule_ProvidesFeedsSettingsDatastoreFactory implements Factory<DataStore<FeedsSettings>> {
  private final Provider<Context> contextProvider;

  private FeedsSettingsModule_ProvidesFeedsSettingsDatastoreFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DataStore<FeedsSettings> get() {
    return providesFeedsSettingsDatastore(contextProvider.get());
  }

  public static FeedsSettingsModule_ProvidesFeedsSettingsDatastoreFactory create(
      Provider<Context> contextProvider) {
    return new FeedsSettingsModule_ProvidesFeedsSettingsDatastoreFactory(contextProvider);
  }

  public static DataStore<FeedsSettings> providesFeedsSettingsDatastore(Context context) {
    return Preconditions.checkNotNullFromProvides(FeedsSettingsModule.INSTANCE.providesFeedsSettingsDatastore(context));
  }
}
