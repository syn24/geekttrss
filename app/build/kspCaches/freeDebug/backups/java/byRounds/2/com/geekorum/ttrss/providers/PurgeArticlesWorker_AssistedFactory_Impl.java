package com.geekorum.ttrss.providers;

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
public final class PurgeArticlesWorker_AssistedFactory_Impl implements PurgeArticlesWorker_AssistedFactory {
  private final PurgeArticlesWorker_Factory delegateFactory;

  PurgeArticlesWorker_AssistedFactory_Impl(PurgeArticlesWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public PurgeArticlesWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<PurgeArticlesWorker_AssistedFactory> create(
      PurgeArticlesWorker_Factory delegateFactory) {
    return InstanceFactory.create(new PurgeArticlesWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<PurgeArticlesWorker_AssistedFactory> createFactoryProvider(
      PurgeArticlesWorker_Factory delegateFactory) {
    return InstanceFactory.create(new PurgeArticlesWorker_AssistedFactory_Impl(delegateFactory));
  }
}
