package com.geekorum.ttrss.manage_feeds.workers;

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
    topLevelClass = UnsubscribeWorker.class
)
public interface UnsubscribeWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.geekorum.ttrss.manage_feeds.workers.UnsubscribeWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(UnsubscribeWorker_AssistedFactory factory);
}
