package com.geekorum.ttrss.manage_feeds.workers;

import android.content.Context;
import androidx.work.WorkerParameters;
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
public final class UnsubscribeWorker_Factory {
  private final Provider<ManageFeedService> apiServiceProvider;

  private UnsubscribeWorker_Factory(Provider<ManageFeedService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  public UnsubscribeWorker get(Context appContext, WorkerParameters params) {
    return newInstance(appContext, params, apiServiceProvider.get());
  }

  public static UnsubscribeWorker_Factory create(Provider<ManageFeedService> apiServiceProvider) {
    return new UnsubscribeWorker_Factory(apiServiceProvider);
  }

  public static UnsubscribeWorker newInstance(Context appContext, WorkerParameters params,
      ManageFeedService apiService) {
    return new UnsubscribeWorker(appContext, params, apiService);
  }
}
