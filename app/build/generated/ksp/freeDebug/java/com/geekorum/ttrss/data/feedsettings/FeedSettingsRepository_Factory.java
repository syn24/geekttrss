package com.geekorum.ttrss.data.feedsettings;

import androidx.datastore.core.DataStore;
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
public final class FeedSettingsRepository_Factory implements Factory<FeedSettingsRepository> {
  private final Provider<DataStore<FeedsSettings>> datastoreProvider;

  private FeedSettingsRepository_Factory(Provider<DataStore<FeedsSettings>> datastoreProvider) {
    this.datastoreProvider = datastoreProvider;
  }

  @Override
  public FeedSettingsRepository get() {
    return newInstance(datastoreProvider.get());
  }

  public static FeedSettingsRepository_Factory create(
      Provider<DataStore<FeedsSettings>> datastoreProvider) {
    return new FeedSettingsRepository_Factory(datastoreProvider);
  }

  public static FeedSettingsRepository newInstance(DataStore<FeedsSettings> datastore) {
    return new FeedSettingsRepository(datastore);
  }
}
