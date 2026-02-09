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
    topLevelClass = UpdateAccountInfoWorker.class
)
public interface UpdateAccountInfoWorker_HiltModule {
  @Binds
  @IntoMap
  @StringKey("com.geekorum.ttrss.sync.workers.UpdateAccountInfoWorker")
  WorkerAssistedFactory<? extends ListenableWorker> bind(
      UpdateAccountInfoWorker_AssistedFactory factory);
}
