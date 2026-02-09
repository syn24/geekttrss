package com.geekorum.ttrss.manage_feeds.workers;

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
public final class SubscribeWorker_AssistedFactory_Impl implements SubscribeWorker_AssistedFactory {
  private final SubscribeWorker_Factory delegateFactory;

  SubscribeWorker_AssistedFactory_Impl(SubscribeWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SubscribeWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<SubscribeWorker_AssistedFactory> create(
      SubscribeWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SubscribeWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SubscribeWorker_AssistedFactory> createFactoryProvider(
      SubscribeWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SubscribeWorker_AssistedFactory_Impl(delegateFactory));
  }
}
