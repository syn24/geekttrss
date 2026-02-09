package com.geekorum.ttrss.manage_feeds;

import android.app.Application;
import dagger.internal.DaggerGenerated;
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
public final class EditSpecialFeedViewModel_Factory {
  private final Provider<Application> applicationProvider;

  private EditSpecialFeedViewModel_Factory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  public EditSpecialFeedViewModel get(long feedId) {
    return newInstance(feedId, applicationProvider.get());
  }

  public static EditSpecialFeedViewModel_Factory create(Provider<Application> applicationProvider) {
    return new EditSpecialFeedViewModel_Factory(applicationProvider);
  }

  public static EditSpecialFeedViewModel newInstance(long feedId, Application application) {
    return new EditSpecialFeedViewModel(feedId, application);
  }
}
