package com.geekorum.ttrss.di;

import com.geekorum.ttrss.logging.RetrofitInvocationLogger;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class NetworkModule_ProvidesRetrofitInvocationLogger$app_freeDebugFactory implements Factory<RetrofitInvocationLogger> {
  @Override
  public RetrofitInvocationLogger get() {
    return providesRetrofitInvocationLogger$app_freeDebug();
  }

  public static NetworkModule_ProvidesRetrofitInvocationLogger$app_freeDebugFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RetrofitInvocationLogger providesRetrofitInvocationLogger$app_freeDebug() {
    return NetworkModule.INSTANCE.providesRetrofitInvocationLogger$app_freeDebug();
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvidesRetrofitInvocationLogger$app_freeDebugFactory INSTANCE = new NetworkModule_ProvidesRetrofitInvocationLogger$app_freeDebugFactory();
  }
}
