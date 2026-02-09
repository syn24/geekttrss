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
public final class SetStarredAction_Factory_Impl implements SetStarredAction.Factory {
  private final SetStarredAction_Factory delegateFactory;

  SetStarredAction_Factory_Impl(SetStarredAction_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SetStarredAction createSetStarredAction(CoroutineScope scope, long articleId,
      boolean newValue) {
    return delegateFactory.get(articleId, newValue, scope);
  }

  public static Provider<SetStarredAction.Factory> create(
      SetStarredAction_Factory delegateFactory) {
    return InstanceFactory.create(new SetStarredAction_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SetStarredAction.Factory> createFactoryProvider(
      SetStarredAction_Factory delegateFactory) {
    return InstanceFactory.create(new SetStarredAction_Factory_Impl(delegateFactory));
  }
}
