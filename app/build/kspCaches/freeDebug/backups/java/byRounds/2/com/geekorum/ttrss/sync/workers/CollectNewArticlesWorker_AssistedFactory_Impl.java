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
public final class CollectNewArticlesWorker_AssistedFactory_Impl implements CollectNewArticlesWorker_AssistedFactory {
  private final CollectNewArticlesWorker_Factory delegateFactory;

  CollectNewArticlesWorker_AssistedFactory_Impl(CollectNewArticlesWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public CollectNewArticlesWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<CollectNewArticlesWorker_AssistedFactory> create(
      CollectNewArticlesWorker_Factory delegateFactory) {
    return InstanceFactory.create(new CollectNewArticlesWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<CollectNewArticlesWorker_AssistedFactory> createFactoryProvider(
      CollectNewArticlesWorker_Factory delegateFactory) {
    return InstanceFactory.create(new CollectNewArticlesWorker_AssistedFactory_Impl(delegateFactory));
  }
}
