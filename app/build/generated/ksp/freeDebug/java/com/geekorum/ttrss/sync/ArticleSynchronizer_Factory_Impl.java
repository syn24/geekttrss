package com.geekorum.ttrss.sync;

import android.os.Bundle;
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
public final class ArticleSynchronizer_Factory_Impl implements ArticleSynchronizer.Factory {
  private final ArticleSynchronizer_Factory delegateFactory;

  ArticleSynchronizer_Factory_Impl(ArticleSynchronizer_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public ArticleSynchronizer create(Bundle params) {
    return delegateFactory.get(params);
  }

  public static Provider<ArticleSynchronizer.Factory> create(
      ArticleSynchronizer_Factory delegateFactory) {
    return InstanceFactory.create(new ArticleSynchronizer_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<ArticleSynchronizer.Factory> createFactoryProvider(
      ArticleSynchronizer_Factory delegateFactory) {
    return InstanceFactory.create(new ArticleSynchronizer_Factory_Impl(delegateFactory));
  }
}
