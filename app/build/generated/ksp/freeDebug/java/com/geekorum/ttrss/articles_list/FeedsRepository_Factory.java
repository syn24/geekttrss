package com.geekorum.ttrss.articles_list;

import com.geekorum.ttrss.data.FeedsDao;
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
public final class FeedsRepository_Factory implements Factory<FeedsRepository> {
  private final Provider<FeedsDao> feedsDaoProvider;

  private final Provider<ArticlesRepository> articlesRepositoryProvider;

  private FeedsRepository_Factory(Provider<FeedsDao> feedsDaoProvider,
      Provider<ArticlesRepository> articlesRepositoryProvider) {
    this.feedsDaoProvider = feedsDaoProvider;
    this.articlesRepositoryProvider = articlesRepositoryProvider;
  }

  @Override
  public FeedsRepository get() {
    return newInstance(feedsDaoProvider.get(), articlesRepositoryProvider.get());
  }

  public static FeedsRepository_Factory create(Provider<FeedsDao> feedsDaoProvider,
      Provider<ArticlesRepository> articlesRepositoryProvider) {
    return new FeedsRepository_Factory(feedsDaoProvider, articlesRepositoryProvider);
  }

  public static FeedsRepository newInstance(FeedsDao feedsDao,
      ArticlesRepository articlesRepository) {
    return new FeedsRepository(feedsDao, articlesRepository);
  }
}
