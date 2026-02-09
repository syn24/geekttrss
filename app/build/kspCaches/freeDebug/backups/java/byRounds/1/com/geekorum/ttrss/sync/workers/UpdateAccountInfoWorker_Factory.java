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
public final class UpdateAccountInfoWorker_Factory {
  private final Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider;

  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private UpdateAccountInfoWorker_Factory(
      Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider) {
    this.syncWorkerComponentBuilderProvider = syncWorkerComponentBuilderProvider;
    this.dispatchersProvider = dispatchersProvider;
  }

  public UpdateAccountInfoWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, syncWorkerComponentBuilderProvider.get(), dispatchersProvider.get());
  }

  public static UpdateAccountInfoWorker_Factory create(
      Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider) {
    return new UpdateAccountInfoWorker_Factory(syncWorkerComponentBuilderProvider, dispatchersProvider);
  }

  public static UpdateAccountInfoWorker newInstance(Context context, WorkerParameters workerParams,
      SyncWorkerComponent.Builder syncWorkerComponentBuilder,
      CoroutineDispatchersProvider dispatchers) {
    return new UpdateAccountInfoWorker(context, workerParams, syncWorkerComponentBuilder, dispatchers);
  }
}
