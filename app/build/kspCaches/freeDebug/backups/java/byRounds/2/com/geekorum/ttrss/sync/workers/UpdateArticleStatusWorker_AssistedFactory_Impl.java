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
public final class UpdateArticleStatusWorker_AssistedFactory_Impl implements UpdateArticleStatusWorker_AssistedFactory {
  private final UpdateArticleStatusWorker_Factory delegateFactory;

  UpdateArticleStatusWorker_AssistedFactory_Impl(
      UpdateArticleStatusWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public UpdateArticleStatusWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<UpdateArticleStatusWorker_AssistedFactory> create(
      UpdateArticleStatusWorker_Factory delegateFactory) {
    return InstanceFactory.create(new UpdateArticleStatusWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<UpdateArticleStatusWorker_AssistedFactory> createFactoryProvider(
      UpdateArticleStatusWorker_Factory delegateFactory) {
    return InstanceFactory.create(new UpdateArticleStatusWorker_AssistedFactory_Impl(delegateFactory));
  }
}
