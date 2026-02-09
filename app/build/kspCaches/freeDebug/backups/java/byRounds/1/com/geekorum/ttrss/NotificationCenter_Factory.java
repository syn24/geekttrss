package com.geekorum.ttrss;

import android.app.NotificationManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class NotificationCenter_Factory implements Factory<NotificationCenter> {
  private final Provider<NotificationManager> notificationManagerProvider;

  private NotificationCenter_Factory(Provider<NotificationManager> notificationManagerProvider) {
    this.notificationManagerProvider = notificationManagerProvider;
  }

  @Override
  public NotificationCenter get() {
    return newInstance(notificationManagerProvider.get());
  }

  public static NotificationCenter_Factory create(
      Provider<NotificationManager> notificationManagerProvider) {
    return new NotificationCenter_Factory(notificationManagerProvider);
  }

  public static NotificationCenter newInstance(NotificationManager notificationManager) {
    return new NotificationCenter(notificationManager);
  }
}
