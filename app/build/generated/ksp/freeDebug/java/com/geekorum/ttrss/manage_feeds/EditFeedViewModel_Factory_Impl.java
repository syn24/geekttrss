package com.geekorum.ttrss.manage_feeds;

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
public final class EditFeedViewModel_Factory_Impl implements EditFeedViewModel.Factory {
  private final EditFeedViewModel_Factory delegateFactory;

  EditFeedViewModel_Factory_Impl(EditFeedViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public EditFeedViewModel create(long feedId) {
    return delegateFactory.get(feedId);
  }

  public static Provider<EditFeedViewModel.Factory> create(
      EditFeedViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new EditFeedViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<EditFeedViewModel.Factory> createFactoryProvider(
      EditFeedViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new EditFeedViewModel_Factory_Impl(delegateFactory));
  }
}
