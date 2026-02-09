package com.geekorum.ttrss.articles_list;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineScope;

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
public final class SetPublishedAction_Factory_Impl implements SetPublishedAction.Factory {
  private final SetPublishedAction_Factory delegateFactory;

  SetPublishedAction_Factory_Impl(SetPublishedAction_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SetPublishedAction createSetPublishedAction(CoroutineScope scope, long articleId,
      boolean newValue) {
    return delegateFactory.get(articleId, newValue, scope);
  }

  public static Provider<SetPublishedAction.Factory> create(
      SetPublishedAction_Factory delegateFactory) {
    return InstanceFactory.create(new SetPublishedAction_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SetPublishedAction.Factory> createFactoryProvider(
      SetPublishedAction_Factory delegateFactory) {
    return InstanceFactory.create(new SetPublishedAction_Factory_Impl(delegateFactory));
  }
}
