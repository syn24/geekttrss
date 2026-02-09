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
public final class UnsubscribeWorker_AssistedFactory_Impl implements UnsubscribeWorker_AssistedFactory {
  private final UnsubscribeWorker_Factory delegateFactory;

  UnsubscribeWorker_AssistedFactory_Impl(UnsubscribeWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public UnsubscribeWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<UnsubscribeWorker_AssistedFactory> create(
      UnsubscribeWorker_Factory delegateFactory) {
    return InstanceFactory.create(new UnsubscribeWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<UnsubscribeWorker_AssistedFactory> createFactoryProvider(
      UnsubscribeWorker_Factory delegateFactory) {
    return InstanceFactory.create(new UnsubscribeWorker_AssistedFactory_Impl(delegateFactory));
  }
}
