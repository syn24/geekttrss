package com.geekorum.ttrss.sync.workers;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import dagger.internal.DaggerGenerated;
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
public final class SendTransactionsWorker_Factory {
  private final Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider;

  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private SendTransactionsWorker_Factory(
      Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider) {
    this.syncWorkerComponentBuilderProvider = syncWorkerComponentBuilderProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  public SendTransactionsWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, syncWorkerComponentBuilderProvider.get(), dispatchersProvider.get());
  }

  public static SendTransactionsWorker_Factory create(
      Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider) {
    return new SendTransactionsWorker_Factory(syncWorkerComponentBuilderProvider, dispatchersProvider);
  }

  public static SendTransactionsWorker newInstance(Context context, WorkerParameters workerParams,
      SyncWorkerComponent.Builder syncWorkerComponentBuilder,
      CoroutineDispatchersProvider dispatchers) {
    return new SendTransactionsWorker(context, workerParams, syncWorkerComponentBuilder, dispatchers);
  }
}
