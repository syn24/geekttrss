package com.geekorum.ttrss.sync.workers;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import com.geekorum.ttrss.htmlparsers.ImageUrlExtractor;
import com.geekorum.ttrss.sync.BackgroundDataUsageManager;
import com.geekorum.ttrss.sync.HttpCacher;
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
public final class CollectNewArticlesWorker_Factory {
  private final Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider;

  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<BackgroundDataUsageManager> backgroundDataUsageManagerProvider;

  private final Provider<ImageUrlExtractor> imageUrlExtractorProvider;

  private final Provider<HttpCacher> httpCacherProvider;

  private CollectNewArticlesWorker_Factory(
      Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<BackgroundDataUsageManager> backgroundDataUsageManagerProvider,
      Provider<ImageUrlExtractor> imageUrlExtractorProvider,
      Provider<HttpCacher> httpCacherProvider) {
    this.syncWorkerComponentBuilderProvider = syncWorkerComponentBuilderProvider;
    this.dispatchersProvider = dispatchersProvider;
    this.backgroundDataUsageManagerProvider = backgroundDataUsageManagerProvider;
    this.imageUrlExtractorProvider = imageUrlExtractorProvider;
    this.httpCacherProvider = httpCacherProvider;
  }

  public CollectNewArticlesWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, syncWorkerComponentBuilderProvider.get(), dispatchersProvider.get(), backgroundDataUsageManagerProvider.get(), imageUrlExtractorProvider.get(), httpCacherProvider.get());
  }

  public static CollectNewArticlesWorker_Factory create(
      Provider<SyncWorkerComponent.Builder> syncWorkerComponentBuilderProvider,
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<BackgroundDataUsageManager> backgroundDataUsageManagerProvider,
      Provider<ImageUrlExtractor> imageUrlExtractorProvider,
      Provider<HttpCacher> httpCacherProvider) {
    return new CollectNewArticlesWorker_Factory(syncWorkerComponentBuilderProvider, dispatchersProvider, backgroundDataUsageManagerProvider, imageUrlExtractorProvider, httpCacherProvider);
  }

  public static CollectNewArticlesWorker newInstance(Context context, WorkerParameters workerParams,
      SyncWorkerComponent.Builder syncWorkerComponentBuilder,
      CoroutineDispatchersProvider dispatchers,
      BackgroundDataUsageManager backgroundDataUsageManager, ImageUrlExtractor imageUrlExtractor,
      HttpCacher httpCacher) {
    return new CollectNewArticlesWorker(context, workerParams, syncWorkerComponentBuilder, dispatchers, backgroundDataUsageManager, imageUrlExtractor, httpCacher);
  }
}
