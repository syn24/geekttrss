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
public final class UpdateAccountInfoWorker_AssistedFactory_Impl implements UpdateAccountInfoWorker_AssistedFactory {
  private final UpdateAccountInfoWorker_Factory delegateFactory;

  UpdateAccountInfoWorker_AssistedFactory_Impl(UpdateAccountInfoWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public UpdateAccountInfoWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<UpdateAccountInfoWorker_AssistedFactory> create(
      UpdateAccountInfoWorker_Factory delegateFactory) {
    return InstanceFactory.create(new UpdateAccountInfoWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<UpdateAccountInfoWorker_AssistedFactory> createFactoryProvider(
      UpdateAccountInfoWorker_Factory delegateFactory) {
    return InstanceFactory.create(new UpdateAccountInfoWorker_AssistedFactory_Impl(delegateFactory));
  }
}
