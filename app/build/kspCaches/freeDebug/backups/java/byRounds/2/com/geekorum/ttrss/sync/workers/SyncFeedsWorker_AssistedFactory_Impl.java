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
public final class SyncFeedsWorker_AssistedFactory_Impl implements SyncFeedsWorker_AssistedFactory {
  private final SyncFeedsWorker_Factory delegateFactory;

  SyncFeedsWorker_AssistedFactory_Impl(SyncFeedsWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SyncFeedsWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<SyncFeedsWorker_AssistedFactory> create(
      SyncFeedsWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SyncFeedsWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SyncFeedsWorker_AssistedFactory> createFactoryProvider(
      SyncFeedsWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SyncFeedsWorker_AssistedFactory_Impl(delegateFactory));
  }
}
