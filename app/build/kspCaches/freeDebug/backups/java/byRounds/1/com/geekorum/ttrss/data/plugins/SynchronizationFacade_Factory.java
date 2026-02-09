package com.geekorum.ttrss.data.plugins;

import com.geekorum.ttrss.data.AccountInfoDao;
import com.geekorum.ttrss.data.ArticlesDatabase;
import com.geekorum.ttrss.data.SynchronizationDao;
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
public final class SynchronizationFacade_Factory implements Factory<SynchronizationFacade> {
  private final Provider<ArticlesDatabase> databaseProvider;

  private final Provider<SynchronizationDao> synchronizationDaoProvider;

  private final Provider<AccountInfoDao> accountInfoDaoProvider;

  private SynchronizationFacade_Factory(Provider<ArticlesDatabase> databaseProvider,
      Provider<SynchronizationDao> synchronizationDaoProvider,
      Provider<AccountInfoDao> accountInfoDaoProvider) {
    this.databaseProvider = databaseProvider;
    this.synchronizationDaoProvider = synchronizationDaoProvider;
    this.accountInfoDaoProvider = accountInfoDaoProvider;
  }

  @Override
  public SynchronizationFacade get() {
    return newInstance(databaseProvider.get(), synchronizationDaoProvider.get(), accountInfoDaoProvider.get());
  }

  public static SynchronizationFacade_Factory create(Provider<ArticlesDatabase> databaseProvider,
      Provider<SynchronizationDao> synchronizationDaoProvider,
      Provider<AccountInfoDao> accountInfoDaoProvider) {
    return new SynchronizationFacade_Factory(databaseProvider, synchronizationDaoProvider, accountInfoDaoProvider);
  }

  public static SynchronizationFacade newInstance(ArticlesDatabase database,
      SynchronizationDao synchronizationDao, AccountInfoDao accountInfoDao) {
    return new SynchronizationFacade(database, synchronizationDao, accountInfoDao);
  }
}
