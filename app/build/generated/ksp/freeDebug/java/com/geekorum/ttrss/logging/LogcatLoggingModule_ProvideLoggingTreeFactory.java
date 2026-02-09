package com.geekorum.ttrss.logging;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import timber.log.Timber;

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
public final class LogcatLoggingModule_ProvideLoggingTreeFactory implements Factory<Timber.Tree> {
  @Override
  public Timber.Tree get() {
    return provideLoggingTree();
  }

  public static LogcatLoggingModule_ProvideLoggingTreeFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Timber.Tree provideLoggingTree() {
    return Preconditions.checkNotNullFromProvides(LogcatLoggingModule.INSTANCE.provideLoggingTree());
  }

  private static final class InstanceHolder {
    static final LogcatLoggingModule_ProvideLoggingTreeFactory INSTANCE = new LogcatLoggingModule_ProvideLoggingTreeFactory();
  }
}
