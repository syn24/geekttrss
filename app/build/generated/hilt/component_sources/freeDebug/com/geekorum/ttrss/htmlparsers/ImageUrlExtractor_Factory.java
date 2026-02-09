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
public final class ImageUrlExtractor_Factory implements Factory<ImageUrlExtractor> {
  @Override
  public ImageUrlExtractor get() {
    return newInstance();
  }

  public static ImageUrlExtractor_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ImageUrlExtractor newInstance() {
    return new ImageUrlExtractor();
  }

  private static final class InstanceHolder {
    static final ImageUrlExtractor_Factory INSTANCE = new ImageUrlExtractor_Factory();
  }
}
