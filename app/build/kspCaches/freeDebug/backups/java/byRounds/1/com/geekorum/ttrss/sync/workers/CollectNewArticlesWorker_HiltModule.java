package com.geekorum.ttrss.sync.workers;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import javax.annotation.processing.Generated;

@Generated("androidx.hilt.AndroidXHiltProcessor")
@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = CollectNewArticlesWorker.class
)
public interface CollectNewArticlesWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.geekorum.ttrss.sync.workers.CollectNewArticlesWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      CollectNewArticlesWorker_AssistedFactory factory);
}
