package com.geekorum.ttrss.articles_list;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SetArticleFieldAction_Factory_Factory implements Factory<SetArticleFieldAction.Factory> {
  private final Provider<SetUnreadAction.Factory> setUnreadActionFactoryProvider;

  private final Provider<SetStarredAction.Factory> setStarredActionFactoryProvider;

  private final Provider<SetPublishedAction.Factory> setPublishedActionFactoryProvider;

  private SetArticleFieldAction_Factory_Factory(
      Provider<SetUnreadAction.Factory> setUnreadActionFactoryProvider,
      Provider<SetStarredAction.Factory> setStarredActionFactoryProvider,
      Provider<SetPublishedAction.Factory> setPublishedActionFactoryProvider) {
    this.setUnreadActionFactoryProvider = setUnreadActionFactoryProvider;
    this.setStarredActionFactoryProvider = setStarredActionFactoryProvider;
    this.setPublishedActionFactoryProvider = setPublishedActionFactoryProvider;
  }

  @Override
  public SetArticleFieldAction.Factory get() {
    return newInstance(setUnreadActionFactoryProvider.get(), setStarredActionFactoryProvider.get(), setPublishedActionFactoryProvider.get());
  }

  public static SetArticleFieldAction_Factory_Factory create(
      Provider<SetUnreadAction.Factory> setUnreadActionFactoryProvider,
      Provider<SetStarredAction.Factory> setStarredActionFactoryProvider,
      Provider<SetPublishedAction.Factory> setPublishedActionFactoryProvider) {
    return new SetArticleFieldAction_Factory_Factory(setUnreadActionFactoryProvider, setStarredActionFactoryProvider, setPublishedActionFactoryProvider);
  }

  public static SetArticleFieldAction.Factory newInstance(
      SetUnreadAction.Factory setUnreadActionFactory,
      SetStarredAction.Factory setStarredActionFactory,
      SetPublishedAction.Factory setPublishedActionFactory) {
    return new SetArticleFieldAction.Factory(setUnreadActionFactory, setStarredActionFactory, setPublishedActionFactory);
  }
}
