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
public final class SubscribeWorker_Factory {
  private final Provider<ManageFeedService> apiServiceProvider;

  private SubscribeWorker_Factory(Provider<ManageFeedService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  public SubscribeWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, apiServiceProvider.get());
  }

  public static SubscribeWorker_Factory create(Provider<ManageFeedService> apiServiceProvider) {
    return new SubscribeWorker_Factory(apiServiceProvider);
  }

  public static SubscribeWorker newInstance(Context context, WorkerParameters params,
      ManageFeedService apiService) {
    return new SubscribeWorker(context, params, apiService);
  }
}
