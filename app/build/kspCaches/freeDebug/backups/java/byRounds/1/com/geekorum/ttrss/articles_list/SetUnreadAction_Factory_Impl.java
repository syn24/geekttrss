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
public final class SetUnreadAction_Factory_Impl implements SetUnreadAction.Factory {
  private final SetUnreadAction_Factory delegateFactory;

  SetUnreadAction_Factory_Impl(SetUnreadAction_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public SetUnreadAction createSetUnreadAction(CoroutineScope scope, long articleId,
      boolean newValue) {
    return delegateFactory.get(articleId, newValue, scope);
  }

  public static Provider<SetUnreadAction.Factory> create(SetUnreadAction_Factory delegateFactory) {
    return InstanceFactory.create(new SetUnreadAction_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<SetUnreadAction.Factory> createFactoryProvider(
      SetUnreadAction_Factory delegateFactory) {
    return InstanceFactory.create(new SetUnreadAction_Factory_Impl(delegateFactory));
  }
}
