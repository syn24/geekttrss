package com.geekorum.ttrss.htmlparsers;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class FeedExtractor_Factory implements Factory<FeedExtractor> {
  @Override
  public FeedExtractor get() {
    return newInstance();
  }

  public static FeedExtractor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FeedExtractor newInstance() {
    return new FeedExtractor();
  }

  private static final class InstanceHolder {
    static final FeedExtractor_Factory INSTANCE = new FeedExtractor_Factory();
  }
}
