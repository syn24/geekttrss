package com.geekorum.ttrss.sync.workers;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SyncFeedsIconWorker_AssistedFactory_Impl implements SyncFeedsIconWorker_AssistedFactory {
  private final SyncFeedsIconWorker_Factory delegateFactory;

  SyncFeedsIconWorker_AssistedFactory_Impl(SyncFeedsIconWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SyncFeedsIconWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<SyncFeedsIconWorker_AssistedFactory> create(
      SyncFeedsIconWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SyncFeedsIconWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SyncFeedsIconWorker_AssistedFactory> createFactoryProvider(
      SyncFeedsIconWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SyncFeedsIconWorker_AssistedFactory_Impl(delegateFactory));
  }
}
