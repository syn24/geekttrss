package com.geekorum.ttrss.providers;

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
    topLevelClass = PurgeArticlesWorker.class
)
public interface PurgeArticlesWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.geekorum.ttrss.providers.PurgeArticlesWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      PurgeArticlesWorker_AssistedFactory factory);
}
