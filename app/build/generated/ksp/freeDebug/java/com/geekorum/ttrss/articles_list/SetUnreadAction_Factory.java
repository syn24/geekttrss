package com.geekorum.ttrss.articles_list;

import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import com.geekorum.ttrss.data.ArticleDao;
import com.geekorum.ttrss.data.TransactionsDao;
import com.geekorum.ttrss.network.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;

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
public final class SetUnreadAction_Factory {
  private final Provider<CoroutineDispatchersProvider> dispatchersProvider;

  private final Provider<ArticleDao> articleDaoProvider;

  private final Provider<TransactionsDao> transactionsDaoProvider;

  private final Provider<ApiService> apiServiceProvider;

  private SetUnreadAction_Factory(Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<ArticleDao> articleDaoProvider, Provider<TransactionsDao> transactionsDaoProvider,
      Provider<ApiService> apiServiceProvider) {
    this.dispatchersProvider = dispatchersProvider;
    this.articleDaoProvider = articleDaoProvider;
    this.transactionsDaoProvider = transactionsDaoProvider;
    this.apiServiceProvider = apiServiceProvider;
  }

  public SetUnreadAction get(long articleId, boolean newValue, CoroutineScope scope) {
    return newInstance(dispatchersProvider.get(), articleDaoProvider.get(), transactionsDaoProvider.get(), apiServiceProvider.get(), articleId, newValue, scope);
  }

  public static SetUnreadAction_Factory create(
      Provider<CoroutineDispatchersProvider> dispatchersProvider,
      Provider<ArticleDao> articleDaoProvider, Provider<TransactionsDao> transactionsDaoProvider,
      Provider<ApiService> apiServiceProvider) {
    return new SetUnreadAction_Factory(dispatchersProvider, articleDaoProvider, transactionsDaoProvider, apiServiceProvider);
  }

  public static SetUnreadAction newInstance(CoroutineDispatchersProvider dispatchers,
      ArticleDao articleDao, TransactionsDao transactionsDao, ApiService apiService, long articleId,
      boolean newValue, CoroutineScope scope) {
    return new SetUnreadAction(dispatchers, articleDao, transactionsDao, apiService, articleId, newValue, scope);
  }
}
