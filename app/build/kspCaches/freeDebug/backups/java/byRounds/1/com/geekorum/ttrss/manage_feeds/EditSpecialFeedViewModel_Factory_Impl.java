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
public final class EditSpecialFeedViewModel_Factory_Impl implements EditSpecialFeedViewModel.Factory {
  private final EditSpecialFeedViewModel_Factory delegateFactory;

  EditSpecialFeedViewModel_Factory_Impl(EditSpecialFeedViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public EditSpecialFeedViewModel create(long feedId) {
    return delegateFactory.get(feedId);
  }

  public static Provider<EditSpecialFeedViewModel.Factory> create(
      EditSpecialFeedViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new EditSpecialFeedViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<EditSpecialFeedViewModel.Factory> createFactoryProvider(
      EditSpecialFeedViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new EditSpecialFeedViewModel_Factory_Impl(delegateFactory));
  }
}
