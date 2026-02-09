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
public final class SendTransactionsWorker_AssistedFactory_Impl implements SendTransactionsWorker_AssistedFactory {
  private final SendTransactionsWorker_Factory delegateFactory;

  SendTransactionsWorker_AssistedFactory_Impl(SendTransactionsWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SendTransactionsWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<SendTransactionsWorker_AssistedFactory> create(
      SendTransactionsWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SendTransactionsWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SendTransactionsWorker_AssistedFactory> createFactoryProvider(
      SendTransactionsWorker_Factory delegateFactory) {
    return InstanceFactory.create(new SendTransactionsWorker_AssistedFactory_Impl(delegateFactory));
  }
}
