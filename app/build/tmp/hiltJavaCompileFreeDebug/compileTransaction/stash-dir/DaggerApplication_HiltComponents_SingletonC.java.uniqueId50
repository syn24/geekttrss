package com.geekorum.ttrss;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.security.NetworkSecurityPolicy;
import android.view.View;
import androidx.datastore.core.DataStore;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.Configuration;
import androidx.work.ListenableWorker;
import androidx.work.WorkManager;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;
import coil.ImageLoader;
import com.geekorum.favikonsnoop.FaviKonSnoop;
import com.geekorum.geekdroid.accounts.AccountSelector;
import com.geekorum.geekdroid.dagger.AndroidFrameworkModule;
import com.geekorum.geekdroid.dagger.AndroidFrameworkModule_ProvidesAccountManagerFactory;
import com.geekorum.geekdroid.dagger.AndroidFrameworkModule_ProvidesConnectivityManagerFactory;
import com.geekorum.geekdroid.dagger.AndroidFrameworkModule_ProvidesNetSecurityPolicyFactory;
import com.geekorum.geekdroid.dagger.AndroidFrameworkModule_ProvidesPackageManagerFactory;
import com.geekorum.geekdroid.dagger.AndroidFrameworkModule_ProvidesPowerManagerFactory;
import com.geekorum.geekdroid.dagger.DaggerDelegateFragmentFactory;
import com.geekorum.geekdroid.network.BrowserLauncher;
import com.geekorum.geekdroid.security.SecretEncryption;
import com.geekorum.ttrss.accounts.AccountAuthenticator;
import com.geekorum.ttrss.accounts.AndroidTinyrssAccountManager;
import com.geekorum.ttrss.accounts.AndroidTinyrssAccountManagerModule_ProvidesAndroidTinyrssAccountManagerFactory;
import com.geekorum.ttrss.accounts.AndroidTinyrssAccountManagerModule_ProvidesTinyrssAccountManagerFactory;
import com.geekorum.ttrss.accounts.AuthenticatorNetworkComponent;
import com.geekorum.ttrss.accounts.AuthenticatorService;
import com.geekorum.ttrss.accounts.AuthenticatorService_MembersInjector;
import com.geekorum.ttrss.accounts.LoginActivity;
import com.geekorum.ttrss.accounts.LoginActivity_MembersInjector;
import com.geekorum.ttrss.accounts.LoginViewModel;
import com.geekorum.ttrss.accounts.LoginViewModel_HiltModules;
import com.geekorum.ttrss.accounts.LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.accounts.LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.accounts.NetworkLoginModule_ProvidesLoggedRequestInterceptorFactoryFactory;
import com.geekorum.ttrss.accounts.NetworkLoginModule_ProvidesServerInformationFactory;
import com.geekorum.ttrss.accounts.NetworkLoginModule_ProvidesTokenRetrieverFactory;
import com.geekorum.ttrss.accounts.ServerInformation;
import com.geekorum.ttrss.accounts.TinyRssServerInformationModule;
import com.geekorum.ttrss.accounts.TinyRssServerInformationModule_ProvidesServerInformationFactory;
import com.geekorum.ttrss.accounts.TinyrssAccountManager;
import com.geekorum.ttrss.add_feed.AddFeedInstallerActivity;
import com.geekorum.ttrss.add_feed.AddFeedLauncherActivity;
import com.geekorum.ttrss.add_feed.AddFeedLauncherActivity_MembersInjector;
import com.geekorum.ttrss.app_reviews.AppReviewStateManager;
import com.geekorum.ttrss.app_reviews.AppReviewViewModel;
import com.geekorum.ttrss.app_reviews.AppReviewViewModel_HiltModules;
import com.geekorum.ttrss.app_reviews.AppReviewViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.app_reviews.AppReviewViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.app_reviews.NoAppReviewModule_ProvidesAppReviewManagerFactory;
import com.geekorum.ttrss.article_details.ArticleDetailActivity;
import com.geekorum.ttrss.article_details.ArticleDetailsViewModel;
import com.geekorum.ttrss.article_details.ArticleDetailsViewModel_HiltModules;
import com.geekorum.ttrss.article_details.ArticleDetailsViewModel_HiltModules_BindsModule_Bind_LazyMapKey;
import com.geekorum.ttrss.article_details.ArticleDetailsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.article_details.ArticleDetailsWebViewClientFactory;
import com.geekorum.ttrss.article_details.ResourcesWebFontProvider;
import com.geekorum.ttrss.articles_list.ActivityViewModel;
import com.geekorum.ttrss.articles_list.ActivityViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.ActivityViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.articles_list.ActivityViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.articles_list.ArticleListActivity;
import com.geekorum.ttrss.articles_list.ArticlesListByTagViewModel;
import com.geekorum.ttrss.articles_list.ArticlesListByTagViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.ArticlesListByTagViewModel_HiltModules_BindsModule_Bind_LazyMapKey;
import com.geekorum.ttrss.articles_list.ArticlesListByTagViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.articles_list.ArticlesListPreferencesRepository;
import com.geekorum.ttrss.articles_list.ArticlesListViewModel;
import com.geekorum.ttrss.articles_list.ArticlesListViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.ArticlesListViewModel_HiltModules_BindsModule_Bind_LazyMapKey;
import com.geekorum.ttrss.articles_list.ArticlesListViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.articles_list.ArticlesRepository;
import com.geekorum.ttrss.articles_list.FeedsRepository;
import com.geekorum.ttrss.articles_list.FeedsViewModel;
import com.geekorum.ttrss.articles_list.FeedsViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.FeedsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.articles_list.FeedsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.articles_list.SetPublishedAction;
import com.geekorum.ttrss.articles_list.SetStarredAction;
import com.geekorum.ttrss.articles_list.SetUnreadAction;
import com.geekorum.ttrss.articles_list.TagsViewModel;
import com.geekorum.ttrss.articles_list.TagsViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.TagsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.articles_list.TagsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.articles_list.TtrssAccountViewModel;
import com.geekorum.ttrss.articles_list.TtrssAccountViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.TtrssAccountViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.articles_list.TtrssAccountViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.articles_list.magazine.MagazineViewModel;
import com.geekorum.ttrss.articles_list.magazine.MagazineViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.magazine.MagazineViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.articles_list.magazine.MagazineViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.articles_list.search.SearchViewModel;
import com.geekorum.ttrss.articles_list.search.SearchViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.search.SearchViewModel_HiltModules_BindsModule_Bind_LazyMapKey;
import com.geekorum.ttrss.articles_list.search.SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.background_job.BackgroundJobManager;
import com.geekorum.ttrss.background_job.BackgroundJobsModule_Companion_ProvideWorkManagerConfigurationFactory;
import com.geekorum.ttrss.background_job.BackgroundJobsModule_Companion_ProvidesApplicationWorkerFactory$app_freeDebugFactory;
import com.geekorum.ttrss.core.ActualCoroutineDispatchersModule_ProvidesCoroutineDispatchersProviderFactory;
import com.geekorum.ttrss.core.CoroutineDispatchersProvider;
import com.geekorum.ttrss.core.InjectableBaseActivity_MembersInjector;
import com.geekorum.ttrss.data.AccountInfoDao;
import com.geekorum.ttrss.data.ArticleDao;
import com.geekorum.ttrss.data.ArticlesDatabase;
import com.geekorum.ttrss.data.ArticlesDatabaseModule_ProvidesAccountInfoDao$app_freeDebugFactory;
import com.geekorum.ttrss.data.ArticlesDatabaseModule_ProvidesArticleDao$app_freeDebugFactory;
import com.geekorum.ttrss.data.ArticlesDatabaseModule_ProvidesFeedsDao$app_freeDebugFactory;
import com.geekorum.ttrss.data.ArticlesDatabaseModule_ProvidesManageFeedsDao$app_freeDebugFactory;
import com.geekorum.ttrss.data.ArticlesDatabaseModule_ProvidesPurgeArticlesDao$app_freeDebugFactory;
import com.geekorum.ttrss.data.ArticlesDatabaseModule_ProvidesSynchronizationDao$app_freeDebugFactory;
import com.geekorum.ttrss.data.ArticlesDatabaseModule_ProvidesTransactionsDao$app_freeDebugFactory;
import com.geekorum.ttrss.data.ArticlesSearchHistory;
import com.geekorum.ttrss.data.ArticlesSearchHistoryModule_ProvidesArticlesSearchHistoryDatastoreFactory;
import com.geekorum.ttrss.data.ArticlesSearchHistoryRepository;
import com.geekorum.ttrss.data.DiskDatabaseModule_ProvidesAppDatabase$app_freeDebugFactory;
import com.geekorum.ttrss.data.FeedsDao;
import com.geekorum.ttrss.data.ManageFeedsDao;
import com.geekorum.ttrss.data.SynchronizationDao;
import com.geekorum.ttrss.data.TransactionsDao;
import com.geekorum.ttrss.data.feedsettings.FeedSettingsRepository;
import com.geekorum.ttrss.data.feedsettings.FeedsSettings;
import com.geekorum.ttrss.data.feedsettings.FeedsSettingsModule_ProvidesFeedsSettingsDatastoreFactory;
import com.geekorum.ttrss.data.plugins.SynchronizationFacade;
import com.geekorum.ttrss.di.NetworkModule;
import com.geekorum.ttrss.di.NetworkModule_ProvidesCache$app_freeDebugFactory;
import com.geekorum.ttrss.di.NetworkModule_ProvidesImageLoader$app_freeDebugFactory;
import com.geekorum.ttrss.di.NetworkModule_ProvidesOkHttpclient$app_freeDebugFactory;
import com.geekorum.ttrss.features_api.FeaturesWorkerFactory;
import com.geekorum.ttrss.htmlparsers.FeedExtractor;
import com.geekorum.ttrss.htmlparsers.ImageUrlExtractor;
import com.geekorum.ttrss.in_app_update.InAppUpdateViewModel;
import com.geekorum.ttrss.in_app_update.InAppUpdateViewModel_HiltModules;
import com.geekorum.ttrss.in_app_update.InAppUpdateViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.in_app_update.InAppUpdateViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.in_app_update.NoInAppUpdateModule_ProvidesInAppUpdateManagerFactory;
import com.geekorum.ttrss.logging.LogcatLoggingModule_ProvideLoggingTreeFactory;
import com.geekorum.ttrss.logging.RetrofitInvocationLogger;
import com.geekorum.ttrss.manage_feeds.EditFeedViewModel;
import com.geekorum.ttrss.manage_feeds.EditFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.EditFeedViewModel_HiltModules_BindsModule_Bind_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.EditFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.EditSpecialFeedViewModel;
import com.geekorum.ttrss.manage_feeds.EditSpecialFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.EditSpecialFeedViewModel_HiltModules_BindsModule_Bind_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.EditSpecialFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.ManageFeedModule_Companion_ProvideWorkManagerFactory;
import com.geekorum.ttrss.manage_feeds.ManageFeedViewModel;
import com.geekorum.ttrss.manage_feeds.ManageFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.ManageFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.ManageFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.ManageFeedsActivity;
import com.geekorum.ttrss.manage_feeds.add_feed.AddFeedActivity;
import com.geekorum.ttrss.manage_feeds.add_feed.AddFeedViewModel;
import com.geekorum.ttrss.manage_feeds.add_feed.AddFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.add_feed.AddFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.add_feed.AddFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.add_feed.FeedsFinder;
import com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedActivity;
import com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedViewModel;
import com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.manage_feeds.workers.ManageFeedService;
import com.geekorum.ttrss.manage_feeds.workers.RetrofitManageFeedService;
import com.geekorum.ttrss.manage_feeds.workers.SubscribeWorker;
import com.geekorum.ttrss.manage_feeds.workers.SubscribeWorker_AssistedFactory;
import com.geekorum.ttrss.manage_feeds.workers.UnsubscribeWorker;
import com.geekorum.ttrss.manage_feeds.workers.UnsubscribeWorker_AssistedFactory;
import com.geekorum.ttrss.network.ApiService;
import com.geekorum.ttrss.network.TinyrssApiModule_Companion_ProvideApiServiceFactory;
import com.geekorum.ttrss.network.TinyrssApiModule_Companion_ProvidesTinyRssApiFactory;
import com.geekorum.ttrss.network.TtRssBrowserLauncher;
import com.geekorum.ttrss.on_demand_modules.InstallModuleViewModel;
import com.geekorum.ttrss.on_demand_modules.InstallModuleViewModel_HiltModules;
import com.geekorum.ttrss.on_demand_modules.InstallModuleViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.on_demand_modules.InstallModuleViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.on_demand_modules.OnDemandModuleNavHostFragment;
import com.geekorum.ttrss.providers.PurgeArticlesDao;
import com.geekorum.ttrss.providers.PurgeArticlesWorker;
import com.geekorum.ttrss.providers.PurgeArticlesWorker_AssistedFactory;
import com.geekorum.ttrss.publish_article.ShareToPublishArticleActivity;
import com.geekorum.ttrss.publish_article.SharingToPublishViewModel;
import com.geekorum.ttrss.publish_article.SharingToPublishViewModel_HiltModules;
import com.geekorum.ttrss.publish_article.SharingToPublishViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.publish_article.SharingToPublishViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.session.AccountSelectorModule_BindsAccountSelectorFactory;
import com.geekorum.ttrss.session.SessionAccountModule;
import com.geekorum.ttrss.session.SessionAccountModule_ProvidesAccountFactory;
import com.geekorum.ttrss.session.SessionActivityComponent;
import com.geekorum.ttrss.settings.ApplicationPreferencesModule_ProvidesApplicationPreferencesFactory;
import com.geekorum.ttrss.settings.SettingsActivity;
import com.geekorum.ttrss.settings.manage_features.InstallFeatureActivity;
import com.geekorum.ttrss.settings.manage_features.ManageFeaturesFragment;
import com.geekorum.ttrss.settings.manage_features.ManageFeaturesViewModel;
import com.geekorum.ttrss.settings.manage_features.ManageFeaturesViewModel_HiltModules;
import com.geekorum.ttrss.settings.manage_features.ManageFeaturesViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.geekorum.ttrss.settings.manage_features.ManageFeaturesViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.geekorum.ttrss.sync.ArticleSyncAdapter;
import com.geekorum.ttrss.sync.ArticleSyncService;
import com.geekorum.ttrss.sync.ArticleSyncService_MembersInjector;
import com.geekorum.ttrss.sync.ArticleSynchronizer;
import com.geekorum.ttrss.sync.BackgroundDataUsageManager;
import com.geekorum.ttrss.sync.DatabaseService;
import com.geekorum.ttrss.sync.FeedIconApiDownloader;
import com.geekorum.ttrss.sync.FeedIconSynchronizer;
import com.geekorum.ttrss.sync.HttpCacher;
import com.geekorum.ttrss.sync.SyncComponent;
import com.geekorum.ttrss.sync.workers.CollectNewArticlesWorker;
import com.geekorum.ttrss.sync.workers.CollectNewArticlesWorker_AssistedFactory;
import com.geekorum.ttrss.sync.workers.FaviKonModule_ProvidesFaviKonSnoopFactory;
import com.geekorum.ttrss.sync.workers.SendTransactionsWorker;
import com.geekorum.ttrss.sync.workers.SendTransactionsWorker_AssistedFactory;
import com.geekorum.ttrss.sync.workers.SyncFeedsIconWorker;
import com.geekorum.ttrss.sync.workers.SyncFeedsIconWorker_AssistedFactory;
import com.geekorum.ttrss.sync.workers.SyncFeedsWorker;
import com.geekorum.ttrss.sync.workers.SyncFeedsWorker_AssistedFactory;
import com.geekorum.ttrss.sync.workers.SyncWorkerComponent;
import com.geekorum.ttrss.sync.workers.UpdateAccountInfoWorker;
import com.geekorum.ttrss.sync.workers.UpdateAccountInfoWorker_AssistedFactory;
import com.geekorum.ttrss.sync.workers.UpdateArticleStatusWorker;
import com.geekorum.ttrss.sync.workers.UpdateArticleStatusWorker_AssistedFactory;
import com.geekorum.ttrss.webapi.LoggedRequestInterceptorFactory;
import com.geekorum.ttrss.webapi.TinyRssApi;
import com.geekorum.ttrss.webapi.TokenRetriever;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

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
public final class DaggerApplication_HiltComponents_SingletonC {
  private DaggerApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private AndroidFrameworkModule androidFrameworkModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public Builder androidFrameworkModule(AndroidFrameworkModule androidFrameworkModule) {
      this.androidFrameworkModule = Preconditions.checkNotNull(androidFrameworkModule);
      return this;
    }

    public Application_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      if (androidFrameworkModule == null) {
        this.androidFrameworkModule = new AndroidFrameworkModule();
      }
      return new SingletonCImpl(applicationContextModule, androidFrameworkModule);
    }
  }

  private static final class SyncWorkerComponentBuilder implements SyncWorkerComponent.Builder {
    private final SingletonCImpl singletonCImpl;

    private Account seedAccount;

    private SyncWorkerComponentBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public SyncWorkerComponentBuilder seedAccount(Account account) {
      this.seedAccount = Preconditions.checkNotNull(account);
      return this;
    }

    @Override
    public SyncWorkerComponent build() {
      Preconditions.checkBuilderRequirement(seedAccount, Account.class);
      return new SyncWorkerComponentImpl(singletonCImpl, seedAccount);
    }
  }

  private static final class ActivityRetainedCBuilder implements Application_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public Application_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements Application_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public Application_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements Application_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public Application_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements Application_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements Application_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements Application_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class SessionActivityComponentFactory implements SessionActivityComponent.Factory {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SessionActivityComponentFactory(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public SessionActivityComponent newComponent() {
      return new SessionActivityComponentImpl(singletonCImpl, activityRetainedCImpl, new SessionAccountModule());
    }
  }

  private static final class ServiceCBuilder implements Application_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public Application_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class cgta2_AuthenticatorNetworkComponentBuilder implements AuthenticatorNetworkComponent.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl;

    private TinyRssServerInformationModule tinyRssServerInformationModule;

    private cgta2_AuthenticatorNetworkComponentBuilder(SingletonCImpl singletonCImpl,
        ServiceCImpl serviceCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.serviceCImpl = serviceCImpl;
    }

    @Override
    public cgta2_AuthenticatorNetworkComponentBuilder tinyRssServerInformationModule(
        TinyRssServerInformationModule module) {
      this.tinyRssServerInformationModule = Preconditions.checkNotNull(module);
      return this;
    }

    @Override
    public AuthenticatorNetworkComponent build() {
      Preconditions.checkBuilderRequirement(tinyRssServerInformationModule, TinyRssServerInformationModule.class);
      return new cgta2_AuthenticatorNetworkComponentImpl(singletonCImpl, serviceCImpl, tinyRssServerInformationModule);
    }
  }

  private static final class SyncComponentBuilder implements SyncComponent.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl;

    private Account seedAccount;

    private SyncComponentBuilder(SingletonCImpl singletonCImpl, ServiceCImpl serviceCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.serviceCImpl = serviceCImpl;
    }

    @Override
    public SyncComponentBuilder seedAccount(Account account) {
      this.seedAccount = Preconditions.checkNotNull(account);
      return this;
    }

    @Override
    public SyncComponent build() {
      Preconditions.checkBuilderRequirement(seedAccount, Account.class);
      return new SyncComponentImpl(singletonCImpl, serviceCImpl, seedAccount);
    }
  }

  private static final class cgta_AuthenticatorNetworkComponentBuilder implements AuthenticatorNetworkComponent.Builder {
    private final SingletonCImpl singletonCImpl;

    private TinyRssServerInformationModule tinyRssServerInformationModule;

    private cgta_AuthenticatorNetworkComponentBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public cgta_AuthenticatorNetworkComponentBuilder tinyRssServerInformationModule(
        TinyRssServerInformationModule module) {
      this.tinyRssServerInformationModule = Preconditions.checkNotNull(module);
      return this;
    }

    @Override
    public AuthenticatorNetworkComponent build() {
      Preconditions.checkBuilderRequirement(tinyRssServerInformationModule, TinyRssServerInformationModule.class);
      return new cgta_AuthenticatorNetworkComponentImpl(singletonCImpl, tinyRssServerInformationModule);
    }
  }

  private static final class SyncWorkerComponentImpl implements SyncWorkerComponent {
    private final Account seedAccount;

    private final SingletonCImpl singletonCImpl;

    private final SyncWorkerComponentImpl syncWorkerComponentImpl = this;

    Provider<TokenRetriever> providesTokenRetrieverProvider;

    Provider<LoggedRequestInterceptorFactory> providesLoggedRequestInterceptorFactoryProvider;

    Provider<FeedIconSynchronizer.Factory> factoryProvider;

    Provider<FeedIconApiDownloader.Factory> factoryProvider2;

    SyncWorkerComponentImpl(SingletonCImpl singletonCImpl, Account seedAccountParam) {
      this.singletonCImpl = singletonCImpl;
      this.seedAccount = seedAccountParam;
      initialize(seedAccountParam);

    }

    TinyRssApi tinyRssApi() {
      return TinyrssApiModule_Companion_ProvidesTinyRssApiFactory.providesTinyRssApi(singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get(), getServerInformation(), Optional.of(providesLoggedRequestInterceptorFactoryProvider.get()));
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Account seedAccountParam) {
      this.providesTokenRetrieverProvider = DoubleCheck.provider(new SwitchingProvider<TokenRetriever>(singletonCImpl, syncWorkerComponentImpl, 0));
      this.providesLoggedRequestInterceptorFactoryProvider = DoubleCheck.provider(new SwitchingProvider<LoggedRequestInterceptorFactory>(singletonCImpl, syncWorkerComponentImpl, 1));
      this.factoryProvider = SingleCheck.provider(new SwitchingProvider<FeedIconSynchronizer.Factory>(singletonCImpl, syncWorkerComponentImpl, 2));
      this.factoryProvider2 = SingleCheck.provider(new SwitchingProvider<FeedIconApiDownloader.Factory>(singletonCImpl, syncWorkerComponentImpl, 3));
    }

    @Override
    public Account getAccount() {
      return seedAccount;
    }

    @Override
    public ApiService getApiService() {
      return TinyrssApiModule_Companion_ProvideApiServiceFactory.provideApiService(providesTokenRetrieverProvider.get(), tinyRssApi(), getServerInformation());
    }

    @Override
    public ServerInformation getServerInformation() {
      return NetworkLoginModule_ProvidesServerInformationFactory.providesServerInformation(singletonCImpl.getAndroidTinyrssAccountManager(), seedAccount);
    }

    @Override
    public DatabaseService getDatabaseService() {
      return singletonCImpl.synchronizationFacade();
    }

    @Override
    public FeedIconSynchronizer.Factory getFeedIconSynchronizerFactory() {
      return factoryProvider.get();
    }

    @Override
    public FeedIconApiDownloader.Factory getFeedIconApiDownloaderFactory() {
      return factoryProvider2.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final SyncWorkerComponentImpl syncWorkerComponentImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl,
          SyncWorkerComponentImpl syncWorkerComponentImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.syncWorkerComponentImpl = syncWorkerComponentImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.geekorum.ttrss.webapi.TokenRetriever
          return (T) NetworkLoginModule_ProvidesTokenRetrieverFactory.providesTokenRetriever(singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.providesAccountManagerProvider.get(), syncWorkerComponentImpl.seedAccount);

          case 1: // com.geekorum.ttrss.webapi.LoggedRequestInterceptorFactory
          return (T) NetworkLoginModule_ProvidesLoggedRequestInterceptorFactoryFactory.providesLoggedRequestInterceptorFactory(syncWorkerComponentImpl.providesTokenRetrieverProvider.get());

          case 2: // com.geekorum.ttrss.sync.FeedIconSynchronizer.Factory
          return (T) new FeedIconSynchronizer.Factory() {
            @Override
            public FeedIconSynchronizer create(FeedIconApiDownloader feedIconApiDownloader) {
              return new FeedIconSynchronizer(singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.synchronizationFacade(), syncWorkerComponentImpl.getApiService(), singletonCImpl.faviKonSnoop(), singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get(), singletonCImpl.httpCacher(), singletonCImpl.providesNetSecurityPolicyProvider.get(), feedIconApiDownloader);
            }
          };

          case 3: // com.geekorum.ttrss.sync.FeedIconApiDownloader.Factory
          return (T) new FeedIconApiDownloader.Factory() {
            @Override
            public FeedIconApiDownloader create(File downloadDir) {
              return new FeedIconApiDownloader(syncWorkerComponentImpl.getApiService(), downloadDir);
            }
          };

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ViewWithFragmentCImpl extends Application_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends Application_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectManageFeaturesFragment(ManageFeaturesFragment manageFeaturesFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends Application_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends Application_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    Provider<SettingsActivity.SettingsFragment> settingsFragmentProvider;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(activityParam);

    }

    Map<Class<? extends Fragment>, javax.inject.Provider<Fragment>> mapOfClassOfAndProviderOfFragment(
        ) {
      return ImmutableMap.<Class<? extends Fragment>, javax.inject.Provider<Fragment>>of(OnDemandModuleNavHostFragment.class, ((Provider) (singletonCImpl.onDemandModuleNavHostFragmentProvider)), SettingsActivity.SettingsFragment.class, ((Provider) (settingsFragmentProvider)));
    }

    DaggerDelegateFragmentFactory daggerDelegateFragmentFactory() {
      return new DaggerDelegateFragmentFactory(mapOfClassOfAndProviderOfFragment());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Activity activityParam) {
      this.settingsFragmentProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, activityCImpl, 0);
    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public void injectSingleActivity(SingleActivity singleActivity) {
      injectSingleActivity2(singleActivity);
    }

    @Override
    public void injectLoginActivity(LoginActivity loginActivity) {
      injectLoginActivity2(loginActivity);
    }

    @Override
    public void injectAddFeedInstallerActivity(AddFeedInstallerActivity addFeedInstallerActivity) {
      injectAddFeedInstallerActivity2(addFeedInstallerActivity);
    }

    @Override
    public void injectAddFeedLauncherActivity(AddFeedLauncherActivity addFeedLauncherActivity) {
      injectAddFeedLauncherActivity2(addFeedLauncherActivity);
    }

    @Override
    public void injectArticleDetailActivity(ArticleDetailActivity articleDetailActivity) {
      injectArticleDetailActivity2(articleDetailActivity);
    }

    @Override
    public ArticleDetailsWebViewClientFactory getArticleDetailsWebViewClientFactory() {
      return new ArticleDetailsWebViewClientFactory(singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get(), singletonCImpl.resourcesWebFontProvider());
    }

    @Override
    public void injectArticleListActivity(ArticleListActivity articleListActivity) {
      injectArticleListActivity2(articleListActivity);
    }

    @Override
    public void injectManageFeedsActivity(ManageFeedsActivity manageFeedsActivity) {
      injectManageFeedsActivity2(manageFeedsActivity);
    }

    @Override
    public void injectAddFeedActivity(AddFeedActivity addFeedActivity) {
    }

    @Override
    public void injectSubscribeToFeedActivity(SubscribeToFeedActivity subscribeToFeedActivity) {
      injectSubscribeToFeedActivity2(subscribeToFeedActivity);
    }

    @Override
    public void injectShareToPublishArticleActivity(
        ShareToPublishArticleActivity shareToPublishArticleActivity) {
    }

    @Override
    public void injectSettingsActivity(SettingsActivity settingsActivity) {
      injectSettingsActivity2(settingsActivity);
    }

    @Override
    public void injectInstallFeatureActivity(InstallFeatureActivity installFeatureActivity) {
      injectInstallFeatureActivity2(installFeatureActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(21).put(ActivityViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ActivityViewModel_HiltModules.KeyModule.provide()).put(AddFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AddFeedViewModel_HiltModules.KeyModule.provide()).put(AppReviewViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, AppReviewViewModel_HiltModules.KeyModule.provide()).put(ArticleDetailsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ArticleDetailsViewModel_HiltModules.KeyModule.provide()).put(ArticlesListByTagViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ArticlesListByTagViewModel_HiltModules.KeyModule.provide()).put(ArticlesListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ArticlesListViewModel_HiltModules.KeyModule.provide()).put(EditFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, EditFeedViewModel_HiltModules.KeyModule.provide()).put(EditSpecialFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, EditSpecialFeedViewModel_HiltModules.KeyModule.provide()).put(FeedsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, FeedsViewModel_HiltModules.KeyModule.provide()).put(ForceNightModeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ForceNightModeViewModel_HiltModules.KeyModule.provide()).put(InAppUpdateViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, InAppUpdateViewModel_HiltModules.KeyModule.provide()).put(InstallModuleViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, InstallModuleViewModel_HiltModules.KeyModule.provide()).put(LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, LoginViewModel_HiltModules.KeyModule.provide()).put(MagazineViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, MagazineViewModel_HiltModules.KeyModule.provide()).put(ManageFeaturesViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ManageFeaturesViewModel_HiltModules.KeyModule.provide()).put(ManageFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ManageFeedViewModel_HiltModules.KeyModule.provide()).put(SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SearchViewModel_HiltModules.KeyModule.provide()).put(SharingToPublishViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SharingToPublishViewModel_HiltModules.KeyModule.provide()).put(SubscribeToFeedViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SubscribeToFeedViewModel_HiltModules.KeyModule.provide()).put(TagsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, TagsViewModel_HiltModules.KeyModule.provide()).put(TtrssAccountViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, TtrssAccountViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance, daggerDelegateFragmentFactory());
      return instance;
    }

    @CanIgnoreReturnValue
    private SingleActivity injectSingleActivity2(SingleActivity instance2) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance2, daggerDelegateFragmentFactory());
      return instance2;
    }

    @CanIgnoreReturnValue
    private LoginActivity injectLoginActivity2(LoginActivity instance3) {
      LoginActivity_MembersInjector.injectAccountManager(instance3, singletonCImpl.getAndroidTinyrssAccountManager());
      return instance3;
    }

    @CanIgnoreReturnValue
    private AddFeedInstallerActivity injectAddFeedInstallerActivity2(
        AddFeedInstallerActivity instance4) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance4, daggerDelegateFragmentFactory());
      return instance4;
    }

    @CanIgnoreReturnValue
    private AddFeedLauncherActivity injectAddFeedLauncherActivity2(
        AddFeedLauncherActivity instance5) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance5, daggerDelegateFragmentFactory());
      AddFeedLauncherActivity_MembersInjector.injectModuleManager(instance5, AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory.providesOnDemandModuleManager());
      return instance5;
    }

    @CanIgnoreReturnValue
    private ArticleDetailActivity injectArticleDetailActivity2(ArticleDetailActivity instance6) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance6, daggerDelegateFragmentFactory());
      return instance6;
    }

    @CanIgnoreReturnValue
    private ArticleListActivity injectArticleListActivity2(ArticleListActivity instance7) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance7, daggerDelegateFragmentFactory());
      return instance7;
    }

    @CanIgnoreReturnValue
    private ManageFeedsActivity injectManageFeedsActivity2(ManageFeedsActivity instance8) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance8, daggerDelegateFragmentFactory());
      return instance8;
    }

    @CanIgnoreReturnValue
    private SubscribeToFeedActivity injectSubscribeToFeedActivity2(
        SubscribeToFeedActivity instance9) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance9, daggerDelegateFragmentFactory());
      return instance9;
    }

    @CanIgnoreReturnValue
    private SettingsActivity injectSettingsActivity2(SettingsActivity instance10) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance10, daggerDelegateFragmentFactory());
      return instance10;
    }

    @CanIgnoreReturnValue
    private InstallFeatureActivity injectInstallFeatureActivity2(
        InstallFeatureActivity instance11) {
      InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance11, daggerDelegateFragmentFactory());
      return instance11;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ActivityCImpl activityCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ActivityCImpl activityCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.activityCImpl = activityCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.geekorum.ttrss.settings.SettingsActivity.SettingsFragment
          return (T) new SettingsActivity.SettingsFragment(singletonCImpl.providesPackageManagerProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ViewModelCImpl extends Application_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<ActivityViewModel> activityViewModelProvider;

    Provider<AddFeedViewModel> addFeedViewModelProvider;

    Provider<AppReviewViewModel> appReviewViewModelProvider;

    Provider<FeedsViewModel> feedsViewModelProvider;

    Provider<ForceNightModeViewModel> forceNightModeViewModelProvider;

    Provider<InAppUpdateViewModel> inAppUpdateViewModelProvider;

    Provider<InstallModuleViewModel> installModuleViewModelProvider;

    Provider<LoginViewModel> loginViewModelProvider;

    Provider<MagazineViewModel> magazineViewModelProvider;

    Provider<ManageFeaturesViewModel> manageFeaturesViewModelProvider;

    Provider<ManageFeedViewModel> manageFeedViewModelProvider;

    Provider<SharingToPublishViewModel> sharingToPublishViewModelProvider;

    Provider<SubscribeToFeedViewModel> subscribeToFeedViewModelProvider;

    Provider<TagsViewModel> tagsViewModelProvider;

    Provider<TtrssAccountViewModel> ttrssAccountViewModelProvider;

    Provider<ArticleDetailsViewModel.Factory> factoryProvider;

    Provider<ArticlesListByTagViewModel.Factory> factoryProvider2;

    Provider<ArticlesListViewModel.Factory> factoryProvider3;

    Provider<EditFeedViewModel.Factory> factoryProvider4;

    Provider<EditSpecialFeedViewModel.Factory> factoryProvider5;

    Provider<SearchViewModel.Factory> factoryProvider6;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    BrowserLauncher browserLauncher() {
      return new BrowserLauncher(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.providesPackageManagerProvider.get());
    }

    TtRssBrowserLauncher ttRssBrowserLauncher() {
      return new TtRssBrowserLauncher(browserLauncher(), singletonCImpl.sharedPreferences(), singletonCImpl.providesPackageManagerProvider.get());
    }

    ArticlesListPreferencesRepository articlesListPreferencesRepository() {
      return new ArticlesListPreferencesRepository(singletonCImpl.sharedPreferences());
    }

    ArticlesSearchHistoryRepository articlesSearchHistoryRepository() {
      return new ArticlesSearchHistoryRepository(singletonCImpl.providesArticlesSearchHistoryDatastoreProvider.get());
    }

    FeedsFinder feedsFinder() {
      return new FeedsFinder(singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get(), new FeedExtractor());
    }

    AppReviewStateManager appReviewStateManager() {
      return new AppReviewStateManager(singletonCImpl.sharedPreferences());
    }

    ArticlesRepository articlesRepository() {
      return new ArticlesRepository(singletonCImpl.articleDao());
    }

    FeedsRepository feedsRepository() {
      return new FeedsRepository(singletonCImpl.feedsDao(), articlesRepository());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.activityViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.addFeedViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.appReviewViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.feedsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.forceNightModeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.inAppUpdateViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.installModuleViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.magazineViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.manageFeaturesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.manageFeedViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.sharingToPublishViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.subscribeToFeedViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.tagsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.ttrssAccountViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
      this.factoryProvider = SingleCheck.provider(new SwitchingProvider<ArticleDetailsViewModel.Factory>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15));
      this.factoryProvider2 = SingleCheck.provider(new SwitchingProvider<ArticlesListByTagViewModel.Factory>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 16));
      this.factoryProvider3 = SingleCheck.provider(new SwitchingProvider<ArticlesListViewModel.Factory>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 17));
      this.factoryProvider4 = SingleCheck.provider(new SwitchingProvider<EditFeedViewModel.Factory>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 18));
      this.factoryProvider5 = SingleCheck.provider(new SwitchingProvider<EditSpecialFeedViewModel.Factory>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 19));
      this.factoryProvider6 = SingleCheck.provider(new SwitchingProvider<SearchViewModel.Factory>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 20));
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(15).put(ActivityViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (activityViewModelProvider))).put(AddFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (addFeedViewModelProvider))).put(AppReviewViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (appReviewViewModelProvider))).put(FeedsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (feedsViewModelProvider))).put(ForceNightModeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (forceNightModeViewModelProvider))).put(InAppUpdateViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (inAppUpdateViewModelProvider))).put(InstallModuleViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (installModuleViewModelProvider))).put(LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (loginViewModelProvider))).put(MagazineViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (magazineViewModelProvider))).put(ManageFeaturesViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (manageFeaturesViewModelProvider))).put(ManageFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (manageFeedViewModelProvider))).put(SharingToPublishViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (sharingToPublishViewModelProvider))).put(SubscribeToFeedViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (subscribeToFeedViewModelProvider))).put(TagsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (tagsViewModelProvider))).put(TtrssAccountViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (ttrssAccountViewModelProvider))).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return LazyClassKeyMap.<Object>of(ImmutableMap.<String, Object>builderWithExpectedSize(6).put(ArticleDetailsViewModel_HiltModules_BindsModule_Bind_LazyMapKey.lazyClassKeyName, factoryProvider.get()).put(ArticlesListByTagViewModel_HiltModules_BindsModule_Bind_LazyMapKey.lazyClassKeyName, factoryProvider2.get()).put(ArticlesListViewModel_HiltModules_BindsModule_Bind_LazyMapKey.lazyClassKeyName, factoryProvider3.get()).put(EditFeedViewModel_HiltModules_BindsModule_Bind_LazyMapKey.lazyClassKeyName, factoryProvider4.get()).put(EditSpecialFeedViewModel_HiltModules_BindsModule_Bind_LazyMapKey.lazyClassKeyName, factoryProvider5.get()).put(SearchViewModel_HiltModules_BindsModule_Bind_LazyMapKey.lazyClassKeyName, factoryProvider6.get()).build());
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.geekorum.ttrss.articles_list.ActivityViewModel
          return (T) new ActivityViewModel(viewModelCImpl.ttRssBrowserLauncher(), viewModelCImpl.articlesListPreferencesRepository(), viewModelCImpl.articlesSearchHistoryRepository(), singletonCImpl.getBackgroundJobManager());

          case 1: // com.geekorum.ttrss.manage_feeds.add_feed.AddFeedViewModel
          return (T) new AddFeedViewModel(singletonCImpl.providesCoroutineDispatchersProvider.get(), viewModelCImpl.feedsFinder(), singletonCImpl.provideWorkManagerProvider.get(), singletonCImpl.providesAccountManagerProvider.get());

          case 2: // com.geekorum.ttrss.app_reviews.AppReviewViewModel
          return (T) new AppReviewViewModel(viewModelCImpl.appReviewStateManager(), NoAppReviewModule_ProvidesAppReviewManagerFactory.providesAppReviewManager());

          case 3: // com.geekorum.ttrss.articles_list.FeedsViewModel
          return (T) new FeedsViewModel(singletonCImpl.providesCoroutineDispatchersProvider.get(), viewModelCImpl.feedsRepository(), viewModelCImpl.articlesRepository(), singletonCImpl.getBackgroundJobManager(), new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));

          case 4: // com.geekorum.ttrss.ForceNightModeViewModel
          return (T) new ForceNightModeViewModel(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.providesPowerManagerProvider.get());

          case 5: // com.geekorum.ttrss.in_app_update.InAppUpdateViewModel
          return (T) new InAppUpdateViewModel(NoInAppUpdateModule_ProvidesInAppUpdateManagerFactory.providesInAppUpdateManager());

          case 6: // com.geekorum.ttrss.on_demand_modules.InstallModuleViewModel
          return (T) new InstallModuleViewModel(AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory.providesOnDemandModuleManager());

          case 7: // com.geekorum.ttrss.accounts.LoginViewModel
          return (T) new LoginViewModel(singletonCImpl.tinyrssAccountManager(), new cgta_AuthenticatorNetworkComponentBuilder(singletonCImpl), singletonCImpl.providesCoroutineDispatchersProvider.get());

          case 8: // com.geekorum.ttrss.articles_list.magazine.MagazineViewModel
          return (T) new MagazineViewModel(singletonCImpl.getBackgroundJobManager(), viewModelCImpl.feedsRepository(), new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));

          case 9: // com.geekorum.ttrss.settings.manage_features.ManageFeaturesViewModel
          return (T) new ManageFeaturesViewModel(AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory.providesOnDemandModuleManager());

          case 10: // com.geekorum.ttrss.manage_feeds.ManageFeedViewModel
          return (T) new ManageFeedViewModel(singletonCImpl.getManageFeedsDao());

          case 11: // com.geekorum.ttrss.publish_article.SharingToPublishViewModel
          return (T) new SharingToPublishViewModel(new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));

          case 12: // com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedViewModel
          return (T) new SubscribeToFeedViewModel(singletonCImpl.providesCoroutineDispatchersProvider.get(), viewModelCImpl.feedsFinder(), singletonCImpl.provideWorkManagerProvider.get(), singletonCImpl.providesAccountManagerProvider.get());

          case 13: // com.geekorum.ttrss.articles_list.TagsViewModel
          return (T) new TagsViewModel(new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));

          case 14: // com.geekorum.ttrss.articles_list.TtrssAccountViewModel
          return (T) new TtrssAccountViewModel(singletonCImpl.providesAccountManagerProvider.get(), singletonCImpl.bindsAccountSelectorProvider.get());

          case 15: // com.geekorum.ttrss.article_details.ArticleDetailsViewModel.Factory
          return (T) new ArticleDetailsViewModel.Factory() {
            @Override
            public ArticleDetailsViewModel create(long articleId) {
              return new ArticleDetailsViewModel(articleId, viewModelCImpl.ttRssBrowserLauncher(), new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));
            }
          };

          case 16: // com.geekorum.ttrss.articles_list.ArticlesListByTagViewModel.Factory
          return (T) new ArticlesListByTagViewModel.Factory() {
            @Override
            public ArticlesListByTagViewModel create(String tag) {
              return new ArticlesListByTagViewModel(tag, singletonCImpl.getBackgroundJobManager(), new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));
            }
          };

          case 17: // com.geekorum.ttrss.articles_list.ArticlesListViewModel.Factory
          return (T) new ArticlesListViewModel.Factory() {
            @Override
            public ArticlesListViewModel create(long feedId) {
              return new ArticlesListViewModel(feedId, viewModelCImpl.feedsRepository(), singletonCImpl.getBackgroundJobManager(), new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));
            }
          };

          case 18: // com.geekorum.ttrss.manage_feeds.EditFeedViewModel.Factory
          return (T) new EditFeedViewModel.Factory() {
            @Override
            public EditFeedViewModel create(long feedId2) {
              return new EditFeedViewModel(feedId2, ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.providesAccountManagerProvider.get(), singletonCImpl.getManageFeedsDao(), singletonCImpl.getFeedsSettingsRepository());
            }
          };

          case 19: // com.geekorum.ttrss.manage_feeds.EditSpecialFeedViewModel.Factory
          return (T) new EditSpecialFeedViewModel.Factory() {
            @Override
            public EditSpecialFeedViewModel create(long feedId3) {
              return new EditSpecialFeedViewModel(feedId3, ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));
            }
          };

          case 20: // com.geekorum.ttrss.articles_list.search.SearchViewModel.Factory
          return (T) new SearchViewModel.Factory() {
            @Override
            public SearchViewModel create(String query) {
              return new SearchViewModel(query, new SessionActivityComponentFactory(singletonCImpl, activityRetainedCImpl));
            }
          };

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class SessionActivityComponentImpl implements SessionActivityComponent {
    private final SessionAccountModule sessionAccountModule;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final SessionActivityComponentImpl sessionActivityComponentImpl = this;

    Provider<TokenRetriever> providesTokenRetrieverProvider;

    Provider<LoggedRequestInterceptorFactory> providesLoggedRequestInterceptorFactoryProvider;

    Provider<SetUnreadAction.Factory> factoryProvider;

    Provider<SetStarredAction.Factory> factoryProvider2;

    Provider<SetPublishedAction.Factory> factoryProvider3;

    SessionActivityComponentImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl,
        SessionAccountModule sessionAccountModuleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.sessionAccountModule = sessionAccountModuleParam;
      initialize(sessionAccountModuleParam);

    }

    ServerInformation serverInformation() {
      return NetworkLoginModule_ProvidesServerInformationFactory.providesServerInformation(singletonCImpl.getAndroidTinyrssAccountManager(), getAccount());
    }

    TinyRssApi tinyRssApi() {
      return TinyrssApiModule_Companion_ProvidesTinyRssApiFactory.providesTinyRssApi(singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get(), serverInformation(), Optional.of(providesLoggedRequestInterceptorFactoryProvider.get()));
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SessionAccountModule sessionAccountModuleParam) {
      this.providesTokenRetrieverProvider = DoubleCheck.provider(new SwitchingProvider<TokenRetriever>(singletonCImpl, activityRetainedCImpl, sessionActivityComponentImpl, 0));
      this.providesLoggedRequestInterceptorFactoryProvider = DoubleCheck.provider(new SwitchingProvider<LoggedRequestInterceptorFactory>(singletonCImpl, activityRetainedCImpl, sessionActivityComponentImpl, 1));
      this.factoryProvider = SingleCheck.provider(new SwitchingProvider<SetUnreadAction.Factory>(singletonCImpl, activityRetainedCImpl, sessionActivityComponentImpl, 2));
      this.factoryProvider2 = SingleCheck.provider(new SwitchingProvider<SetStarredAction.Factory>(singletonCImpl, activityRetainedCImpl, sessionActivityComponentImpl, 3));
      this.factoryProvider3 = SingleCheck.provider(new SwitchingProvider<SetPublishedAction.Factory>(singletonCImpl, activityRetainedCImpl, sessionActivityComponentImpl, 4));
    }

    @Override
    public Account getAccount() {
      return SessionAccountModule_ProvidesAccountFactory.providesAccount(sessionAccountModule, singletonCImpl.bindsAccountSelectorProvider.get());
    }

    @Override
    public ApiService getApiService() {
      return TinyrssApiModule_Companion_ProvideApiServiceFactory.provideApiService(providesTokenRetrieverProvider.get(), tinyRssApi(), serverInformation());
    }

    @Override
    public ArticlesRepository getArticleRepository() {
      return new ArticlesRepository(singletonCImpl.articleDao());
    }

    @Override
    public com.geekorum.ttrss.articles_list.SetArticleFieldAction.Factory getSetArticleFieldActionFactory(
        ) {
      return new com.geekorum.ttrss.articles_list.SetArticleFieldAction.Factory(factoryProvider.get(), factoryProvider2.get(), factoryProvider3.get());
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final SessionActivityComponentImpl sessionActivityComponentImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          SessionActivityComponentImpl sessionActivityComponentImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.sessionActivityComponentImpl = sessionActivityComponentImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.geekorum.ttrss.webapi.TokenRetriever
          return (T) NetworkLoginModule_ProvidesTokenRetrieverFactory.providesTokenRetriever(singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.providesAccountManagerProvider.get(), sessionActivityComponentImpl.getAccount());

          case 1: // com.geekorum.ttrss.webapi.LoggedRequestInterceptorFactory
          return (T) NetworkLoginModule_ProvidesLoggedRequestInterceptorFactoryFactory.providesLoggedRequestInterceptorFactory(sessionActivityComponentImpl.providesTokenRetrieverProvider.get());

          case 2: // com.geekorum.ttrss.articles_list.SetUnreadAction.Factory
          return (T) new SetUnreadAction.Factory() {
            @Override
            public SetUnreadAction createSetUnreadAction(CoroutineScope scope, long articleId,
                boolean newValue) {
              return new SetUnreadAction(singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.articleDao(), singletonCImpl.transactionsDao(), sessionActivityComponentImpl.getApiService(), articleId, newValue, scope);
            }
          };

          case 3: // com.geekorum.ttrss.articles_list.SetStarredAction.Factory
          return (T) new SetStarredAction.Factory() {
            @Override
            public SetStarredAction createSetStarredAction(CoroutineScope scope2, long articleId2,
                boolean newValue2) {
              return new SetStarredAction(singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.articleDao(), singletonCImpl.transactionsDao(), sessionActivityComponentImpl.getApiService(), articleId2, newValue2, scope2);
            }
          };

          case 4: // com.geekorum.ttrss.articles_list.SetPublishedAction.Factory
          return (T) new SetPublishedAction.Factory() {
            @Override
            public SetPublishedAction createSetPublishedAction(CoroutineScope scope3,
                long articleId3, boolean newValue3) {
              return new SetPublishedAction(singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.articleDao(), singletonCImpl.transactionsDao(), sessionActivityComponentImpl.getApiService(), articleId3, newValue3, scope3);
            }
          };

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends Application_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class cgta2_AuthenticatorNetworkComponentImpl implements AuthenticatorNetworkComponent {
    private final TinyRssServerInformationModule tinyRssServerInformationModule;

    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl;

    private final cgta2_AuthenticatorNetworkComponentImpl _cgta2_AuthenticatorNetworkComponentImpl = this;

    cgta2_AuthenticatorNetworkComponentImpl(SingletonCImpl singletonCImpl,
        ServiceCImpl serviceCImpl,
        TinyRssServerInformationModule tinyRssServerInformationModuleParam) {
      this.singletonCImpl = singletonCImpl;
      this.serviceCImpl = serviceCImpl;
      this.tinyRssServerInformationModule = tinyRssServerInformationModuleParam;

    }

    @Override
    public TinyRssApi getTinyRssApi() {
      return TinyrssApiModule_Companion_ProvidesTinyRssApiFactory.providesTinyRssApi(singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get(), TinyRssServerInformationModule_ProvidesServerInformationFactory.providesServerInformation(tinyRssServerInformationModule), Optional.empty());
    }
  }

  private static final class SyncComponentImpl implements SyncComponent {
    private final Account seedAccount;

    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl;

    private final SyncComponentImpl syncComponentImpl = this;

    Provider<ArticleSynchronizer.Factory> factoryProvider;

    SyncComponentImpl(SingletonCImpl singletonCImpl, ServiceCImpl serviceCImpl,
        Account seedAccountParam) {
      this.singletonCImpl = singletonCImpl;
      this.serviceCImpl = serviceCImpl;
      this.seedAccount = seedAccountParam;
      initialize(seedAccountParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final Account seedAccountParam) {
      this.factoryProvider = SingleCheck.provider(new SwitchingProvider<ArticleSynchronizer.Factory>(singletonCImpl, serviceCImpl, syncComponentImpl, 0));
    }

    @Override
    public ArticleSynchronizer.Factory getArticleSynchronizerFactory() {
      return factoryProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ServiceCImpl serviceCImpl;

      private final SyncComponentImpl syncComponentImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ServiceCImpl serviceCImpl,
          SyncComponentImpl syncComponentImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.serviceCImpl = serviceCImpl;
        this.syncComponentImpl = syncComponentImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.geekorum.ttrss.sync.ArticleSynchronizer.Factory
          return (T) new ArticleSynchronizer.Factory() {
            @Override
            public ArticleSynchronizer create(Bundle params) {
              return new ArticleSynchronizer(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), params, syncComponentImpl.seedAccount, singletonCImpl.synchronizationFacade(), singletonCImpl.getFeedsSettingsRepository());
            }
          };

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends Application_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    AccountAuthenticator accountAuthenticator() {
      return new AccountAuthenticator(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.getAndroidTinyrssAccountManager(), singletonCImpl.getBackgroundJobManager(), new cgta2_AuthenticatorNetworkComponentBuilder(singletonCImpl, serviceCImpl));
    }

    ArticleSyncAdapter articleSyncAdapter() {
      return new ArticleSyncAdapter(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), new SyncComponentBuilder(singletonCImpl, serviceCImpl));
    }

    @Override
    public void injectAuthenticatorService(AuthenticatorService authenticatorService) {
      injectAuthenticatorService2(authenticatorService);
    }

    @Override
    public void injectArticleSyncService(ArticleSyncService articleSyncService) {
      injectArticleSyncService2(articleSyncService);
    }

    @CanIgnoreReturnValue
    private AuthenticatorService injectAuthenticatorService2(AuthenticatorService instance) {
      AuthenticatorService_MembersInjector.injectAccountAuthenticator(instance, accountAuthenticator());
      return instance;
    }

    @CanIgnoreReturnValue
    private ArticleSyncService injectArticleSyncService2(ArticleSyncService instance2) {
      ArticleSyncService_MembersInjector.injectArticleSyncAdapter(instance2, articleSyncAdapter());
      return instance2;
    }
  }

  private static final class cgta_AuthenticatorNetworkComponentImpl implements AuthenticatorNetworkComponent {
    private final TinyRssServerInformationModule tinyRssServerInformationModule;

    private final SingletonCImpl singletonCImpl;

    private final cgta_AuthenticatorNetworkComponentImpl _cgta_AuthenticatorNetworkComponentImpl = this;

    cgta_AuthenticatorNetworkComponentImpl(SingletonCImpl singletonCImpl,
        TinyRssServerInformationModule tinyRssServerInformationModuleParam) {
      this.singletonCImpl = singletonCImpl;
      this.tinyRssServerInformationModule = tinyRssServerInformationModuleParam;

    }

    @Override
    public TinyRssApi getTinyRssApi() {
      return TinyrssApiModule_Companion_ProvidesTinyRssApiFactory.providesTinyRssApi(singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get(), TinyRssServerInformationModule_ProvidesServerInformationFactory.providesServerInformation(tinyRssServerInformationModule), Optional.empty());
    }
  }

  private static final class SingletonCImpl extends Application_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final AndroidFrameworkModule androidFrameworkModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<HttpLoggingInterceptor> providesHttpRequestLogger$app_freeDebugProvider;

    Provider<RetrofitInvocationLogger> providesRetrofitInvocationLogger$app_freeDebugProvider;

    Provider<OkHttpClient> providesOkHttpclient$app_freeDebugProvider;

    Provider<ImageLoader> providesImageLoader$app_freeDebugProvider;

    Provider<CoroutineDispatchersProvider> providesCoroutineDispatchersProvider;

    Provider<ConnectivityManager> providesConnectivityManagerProvider;

    Provider<CollectNewArticlesWorker_AssistedFactory> collectNewArticlesWorker_AssistedFactoryProvider;

    Provider<ArticlesDatabase> providesAppDatabase$app_freeDebugProvider;

    Provider<PurgeArticlesWorker_AssistedFactory> purgeArticlesWorker_AssistedFactoryProvider;

    Provider<SendTransactionsWorker_AssistedFactory> sendTransactionsWorker_AssistedFactoryProvider;

    Provider<AccountManager> providesAccountManagerProvider;

    Provider<RetrofitManageFeedService> retrofitManageFeedServiceProvider;

    Provider<ManageFeedService> bindManageFeedService$app_freeDebugProvider;

    Provider<SubscribeWorker_AssistedFactory> subscribeWorker_AssistedFactoryProvider;

    Provider<SyncFeedsIconWorker_AssistedFactory> syncFeedsIconWorker_AssistedFactoryProvider;

    Provider<SyncFeedsWorker_AssistedFactory> syncFeedsWorker_AssistedFactoryProvider;

    Provider<UnsubscribeWorker_AssistedFactory> unsubscribeWorker_AssistedFactoryProvider;

    Provider<UpdateAccountInfoWorker_AssistedFactory> updateAccountInfoWorker_AssistedFactoryProvider;

    Provider<UpdateArticleStatusWorker_AssistedFactory> updateArticleStatusWorker_AssistedFactoryProvider;

    Provider<PowerManager> providesPowerManagerProvider;

    Provider<DataStore<FeedsSettings>> providesFeedsSettingsDatastoreProvider;

    Provider<NetworkSecurityPolicy> providesNetSecurityPolicyProvider;

    Provider<OnDemandModuleNavHostFragment> onDemandModuleNavHostFragmentProvider;

    Provider<PackageManager> providesPackageManagerProvider;

    Provider<DataStore<ArticlesSearchHistory>> providesArticlesSearchHistoryDatastoreProvider;

    Provider<WorkManager> provideWorkManagerProvider;

    Provider<AccountSelector> bindsAccountSelectorProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam,
        AndroidFrameworkModule androidFrameworkModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      this.androidFrameworkModule = androidFrameworkModuleParam;
      initialize(applicationContextModuleParam, androidFrameworkModuleParam);
      initialize2(applicationContextModuleParam, androidFrameworkModuleParam);

    }

    Cache cache() {
      return NetworkModule_ProvidesCache$app_freeDebugFactory.providesCache$app_freeDebug(ApplicationContextModule_ProvideApplicationFactory.provideApplication(applicationContextModule));
    }

    BackgroundDataUsageManager backgroundDataUsageManager() {
      return new BackgroundDataUsageManager(providesConnectivityManagerProvider.get());
    }

    HttpCacher httpCacher() {
      return new HttpCacher(providesOkHttpclient$app_freeDebugProvider.get());
    }

    PurgeArticlesDao purgeArticlesDao() {
      return ArticlesDatabaseModule_ProvidesPurgeArticlesDao$app_freeDebugFactory.providesPurgeArticlesDao$app_freeDebug(providesAppDatabase$app_freeDebugProvider.get());
    }

    Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return ImmutableMap.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>builderWithExpectedSize(9).put("com.geekorum.ttrss.sync.workers.CollectNewArticlesWorker", ((Provider) (collectNewArticlesWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.providers.PurgeArticlesWorker", ((Provider) (purgeArticlesWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.sync.workers.SendTransactionsWorker", ((Provider) (sendTransactionsWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.manage_feeds.workers.SubscribeWorker", ((Provider) (subscribeWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.sync.workers.SyncFeedsIconWorker", ((Provider) (syncFeedsIconWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.sync.workers.SyncFeedsWorker", ((Provider) (syncFeedsWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.manage_feeds.workers.UnsubscribeWorker", ((Provider) (unsubscribeWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.sync.workers.UpdateAccountInfoWorker", ((Provider) (updateAccountInfoWorker_AssistedFactoryProvider))).put("com.geekorum.ttrss.sync.workers.UpdateArticleStatusWorker", ((Provider) (updateArticleStatusWorker_AssistedFactoryProvider))).build();
    }

    HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    WorkerFactory workerFactory() {
      return BackgroundJobsModule_Companion_ProvidesApplicationWorkerFactory$app_freeDebugFactory.providesApplicationWorkerFactory$app_freeDebug(hiltWorkerFactory(), new FeaturesWorkerFactory());
    }

    SynchronizationDao synchronizationDao() {
      return ArticlesDatabaseModule_ProvidesSynchronizationDao$app_freeDebugFactory.providesSynchronizationDao$app_freeDebug(providesAppDatabase$app_freeDebugProvider.get());
    }

    AccountInfoDao accountInfoDao() {
      return ArticlesDatabaseModule_ProvidesAccountInfoDao$app_freeDebugFactory.providesAccountInfoDao$app_freeDebug(providesAppDatabase$app_freeDebugProvider.get());
    }

    SynchronizationFacade synchronizationFacade() {
      return new SynchronizationFacade(providesAppDatabase$app_freeDebugProvider.get(), synchronizationDao(), accountInfoDao());
    }

    FaviKonSnoop faviKonSnoop() {
      return FaviKonModule_ProvidesFaviKonSnoopFactory.providesFaviKonSnoop(providesOkHttpclient$app_freeDebugProvider.get(), providesCoroutineDispatchersProvider.get());
    }

    ResourcesWebFontProvider resourcesWebFontProvider() {
      return new ResourcesWebFontProvider(ApplicationContextModule_ProvideApplicationFactory.provideApplication(applicationContextModule));
    }

    SharedPreferences sharedPreferences() {
      return ApplicationPreferencesModule_ProvidesApplicationPreferencesFactory.providesApplicationPreferences(ApplicationContextModule_ProvideApplicationFactory.provideApplication(applicationContextModule));
    }

    FeedsDao feedsDao() {
      return ArticlesDatabaseModule_ProvidesFeedsDao$app_freeDebugFactory.providesFeedsDao$app_freeDebug(providesAppDatabase$app_freeDebugProvider.get());
    }

    ArticleDao articleDao() {
      return ArticlesDatabaseModule_ProvidesArticleDao$app_freeDebugFactory.providesArticleDao$app_freeDebug(providesAppDatabase$app_freeDebugProvider.get());
    }

    TinyrssAccountManager tinyrssAccountManager() {
      return AndroidTinyrssAccountManagerModule_ProvidesTinyrssAccountManagerFactory.providesTinyrssAccountManager(getAndroidTinyrssAccountManager());
    }

    TransactionsDao transactionsDao() {
      return ArticlesDatabaseModule_ProvidesTransactionsDao$app_freeDebugFactory.providesTransactionsDao$app_freeDebug(providesAppDatabase$app_freeDebugProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam,
        final AndroidFrameworkModule androidFrameworkModuleParam) {
      this.providesHttpRequestLogger$app_freeDebugProvider = DoubleCheck.provider(new SwitchingProvider<HttpLoggingInterceptor>(singletonCImpl, 2));
      this.providesRetrofitInvocationLogger$app_freeDebugProvider = DoubleCheck.provider(new SwitchingProvider<RetrofitInvocationLogger>(singletonCImpl, 3));
      this.providesOkHttpclient$app_freeDebugProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 1));
      this.providesImageLoader$app_freeDebugProvider = DoubleCheck.provider(new SwitchingProvider<ImageLoader>(singletonCImpl, 0));
      this.providesCoroutineDispatchersProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineDispatchersProvider>(singletonCImpl, 5));
      this.providesConnectivityManagerProvider = DoubleCheck.provider(new SwitchingProvider<ConnectivityManager>(singletonCImpl, 6));
      this.collectNewArticlesWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<CollectNewArticlesWorker_AssistedFactory>(singletonCImpl, 4));
      this.providesAppDatabase$app_freeDebugProvider = DoubleCheck.provider(new SwitchingProvider<ArticlesDatabase>(singletonCImpl, 8));
      this.purgeArticlesWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<PurgeArticlesWorker_AssistedFactory>(singletonCImpl, 7));
      this.sendTransactionsWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<SendTransactionsWorker_AssistedFactory>(singletonCImpl, 9));
      this.providesAccountManagerProvider = DoubleCheck.provider(new SwitchingProvider<AccountManager>(singletonCImpl, 12));
      this.retrofitManageFeedServiceProvider = new SwitchingProvider<>(singletonCImpl, 11);
      this.bindManageFeedService$app_freeDebugProvider = DoubleCheck.provider((Provider) (retrofitManageFeedServiceProvider));
      this.subscribeWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<SubscribeWorker_AssistedFactory>(singletonCImpl, 10));
      this.syncFeedsIconWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<SyncFeedsIconWorker_AssistedFactory>(singletonCImpl, 13));
      this.syncFeedsWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<SyncFeedsWorker_AssistedFactory>(singletonCImpl, 14));
      this.unsubscribeWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<UnsubscribeWorker_AssistedFactory>(singletonCImpl, 15));
      this.updateAccountInfoWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<UpdateAccountInfoWorker_AssistedFactory>(singletonCImpl, 16));
      this.updateArticleStatusWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<UpdateArticleStatusWorker_AssistedFactory>(singletonCImpl, 17));
      this.providesPowerManagerProvider = DoubleCheck.provider(new SwitchingProvider<PowerManager>(singletonCImpl, 18));
      this.providesFeedsSettingsDatastoreProvider = DoubleCheck.provider(new SwitchingProvider<DataStore<FeedsSettings>>(singletonCImpl, 19));
      this.providesNetSecurityPolicyProvider = DoubleCheck.provider(new SwitchingProvider<NetworkSecurityPolicy>(singletonCImpl, 20));
      this.onDemandModuleNavHostFragmentProvider = new SwitchingProvider<>(singletonCImpl, 21);
      this.providesPackageManagerProvider = DoubleCheck.provider(new SwitchingProvider<PackageManager>(singletonCImpl, 22));
      this.providesArticlesSearchHistoryDatastoreProvider = DoubleCheck.provider(new SwitchingProvider<DataStore<ArticlesSearchHistory>>(singletonCImpl, 23));
    }

    @SuppressWarnings("unchecked")
    private void initialize2(final ApplicationContextModule applicationContextModuleParam,
        final AndroidFrameworkModule androidFrameworkModuleParam) {
      this.provideWorkManagerProvider = DoubleCheck.provider(new SwitchingProvider<WorkManager>(singletonCImpl, 24));
      this.bindsAccountSelectorProvider = DoubleCheck.provider(new SwitchingProvider<AccountSelector>(singletonCImpl, 25));
    }

    @Override
    public void injectApplication(Application application) {
      injectApplication2(application);
    }

    @Override
    public BackgroundJobManager getBackgroundJobManager() {
      return new BackgroundJobManager(ApplicationContextModule_ProvideApplicationFactory.provideApplication(applicationContextModule));
    }

    @Override
    public Configuration getWorkManagerConfiguration() {
      return BackgroundJobsModule_Companion_ProvideWorkManagerConfigurationFactory.provideWorkManagerConfiguration(workerFactory());
    }

    @Override
    public android.app.Application getApplication() {
      return ApplicationContextModule_ProvideApplicationFactory.provideApplication(applicationContextModule);
    }

    @Override
    public AccountManager getAccountManager() {
      return providesAccountManagerProvider.get();
    }

    @Override
    public PowerManager getPowerManager() {
      return providesPowerManagerProvider.get();
    }

    @Override
    public ManageFeedsDao getManageFeedsDao() {
      return ArticlesDatabaseModule_ProvidesManageFeedsDao$app_freeDebugFactory.providesManageFeedsDao$app_freeDebug(providesAppDatabase$app_freeDebugProvider.get());
    }

    @Override
    public OkHttpClient getOkHttpClient() {
      return providesOkHttpclient$app_freeDebugProvider.get();
    }

    @Override
    public CoroutineDispatchersProvider getCoroutineDispatchersProvider() {
      return providesCoroutineDispatchersProvider.get();
    }

    @Override
    public AndroidTinyrssAccountManager getAndroidTinyrssAccountManager() {
      return AndroidTinyrssAccountManagerModule_ProvidesAndroidTinyrssAccountManagerFactory.providesAndroidTinyrssAccountManager(providesAccountManagerProvider.get(), new SecretEncryption());
    }

    @Override
    public FeedSettingsRepository getFeedsSettingsRepository() {
      return new FeedSettingsRepository(providesFeedsSettingsDatastoreProvider.get());
    }

    @Override
    public Set<Timber.Tree> getTimberTrees() {
      return ImmutableSet.<Timber.Tree>of(LogcatLoggingModule_ProvideLoggingTreeFactory.provideLoggingTree());
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private Application injectApplication2(Application instance) {
      Application_MembersInjector.injectWorkManagerConfig(instance, getWorkManagerConfiguration());
      Application_MembersInjector.injectImageLoader(instance, providesImageLoader$app_freeDebugProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // coil.ImageLoader
          return (T) NetworkModule_ProvidesImageLoader$app_freeDebugFactory.providesImageLoader$app_freeDebug(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get());

          case 1: // okhttp3.OkHttpClient
          return (T) NetworkModule_ProvidesOkHttpclient$app_freeDebugFactory.providesOkHttpclient$app_freeDebug(singletonCImpl.cache(), singletonCImpl.providesHttpRequestLogger$app_freeDebugProvider.get(), singletonCImpl.providesRetrofitInvocationLogger$app_freeDebugProvider.get());

          case 2: // okhttp3.logging.HttpLoggingInterceptor
          return (T) NetworkModule.INSTANCE.providesHttpRequestLogger$app_freeDebug();

          case 3: // com.geekorum.ttrss.logging.RetrofitInvocationLogger
          return (T) NetworkModule.INSTANCE.providesRetrofitInvocationLogger$app_freeDebug();

          case 4: // com.geekorum.ttrss.sync.workers.CollectNewArticlesWorker_AssistedFactory
          return (T) new CollectNewArticlesWorker_AssistedFactory() {
            @Override
            public CollectNewArticlesWorker create(Context context, WorkerParameters workerParams) {
              return new CollectNewArticlesWorker(context, workerParams, new SyncWorkerComponentBuilder(singletonCImpl), singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.backgroundDataUsageManager(), new ImageUrlExtractor(), singletonCImpl.httpCacher());
            }
          };

          case 5: // com.geekorum.ttrss.core.CoroutineDispatchersProvider
          return (T) ActualCoroutineDispatchersModule_ProvidesCoroutineDispatchersProviderFactory.providesCoroutineDispatchersProvider();

          case 6: // android.net.ConnectivityManager
          return (T) AndroidFrameworkModule_ProvidesConnectivityManagerFactory.providesConnectivityManager(singletonCImpl.androidFrameworkModule, ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 7: // com.geekorum.ttrss.providers.PurgeArticlesWorker_AssistedFactory
          return (T) new PurgeArticlesWorker_AssistedFactory() {
            @Override
            public PurgeArticlesWorker create(Context appContext, WorkerParameters params) {
              return new PurgeArticlesWorker(appContext, params, singletonCImpl.purgeArticlesDao());
            }
          };

          case 8: // com.geekorum.ttrss.data.ArticlesDatabase
          return (T) DiskDatabaseModule_ProvidesAppDatabase$app_freeDebugFactory.providesAppDatabase$app_freeDebug(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 9: // com.geekorum.ttrss.sync.workers.SendTransactionsWorker_AssistedFactory
          return (T) new SendTransactionsWorker_AssistedFactory() {
            @Override
            public SendTransactionsWorker create(Context context2, WorkerParameters workerParams2) {
              return new SendTransactionsWorker(context2, workerParams2, new SyncWorkerComponentBuilder(singletonCImpl), singletonCImpl.providesCoroutineDispatchersProvider.get());
            }
          };

          case 10: // com.geekorum.ttrss.manage_feeds.workers.SubscribeWorker_AssistedFactory
          return (T) new SubscribeWorker_AssistedFactory() {
            @Override
            public SubscribeWorker create(Context context3, WorkerParameters params2) {
              return new SubscribeWorker(context3, params2, singletonCImpl.bindManageFeedService$app_freeDebugProvider.get());
            }
          };

          case 11: // com.geekorum.ttrss.manage_feeds.workers.RetrofitManageFeedService
          return (T) new RetrofitManageFeedService(singletonCImpl.providesAccountManagerProvider.get(), singletonCImpl.providesCoroutineDispatchersProvider.get(), singletonCImpl.providesOkHttpclient$app_freeDebugProvider.get());

          case 12: // android.accounts.AccountManager
          return (T) AndroidFrameworkModule_ProvidesAccountManagerFactory.providesAccountManager(singletonCImpl.androidFrameworkModule, ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 13: // com.geekorum.ttrss.sync.workers.SyncFeedsIconWorker_AssistedFactory
          return (T) new SyncFeedsIconWorker_AssistedFactory() {
            @Override
            public SyncFeedsIconWorker create(Context context4, WorkerParameters workerParams3) {
              return new SyncFeedsIconWorker(context4, workerParams3, new SyncWorkerComponentBuilder(singletonCImpl), singletonCImpl.providesCoroutineDispatchersProvider.get());
            }
          };

          case 14: // com.geekorum.ttrss.sync.workers.SyncFeedsWorker_AssistedFactory
          return (T) new SyncFeedsWorker_AssistedFactory() {
            @Override
            public SyncFeedsWorker create(Context context5, WorkerParameters workerParams4) {
              return new SyncFeedsWorker(context5, workerParams4, new SyncWorkerComponentBuilder(singletonCImpl), singletonCImpl.providesCoroutineDispatchersProvider.get());
            }
          };

          case 15: // com.geekorum.ttrss.manage_feeds.workers.UnsubscribeWorker_AssistedFactory
          return (T) new UnsubscribeWorker_AssistedFactory() {
            @Override
            public UnsubscribeWorker create(Context appContext2, WorkerParameters params3) {
              return new UnsubscribeWorker(appContext2, params3, singletonCImpl.bindManageFeedService$app_freeDebugProvider.get());
            }
          };

          case 16: // com.geekorum.ttrss.sync.workers.UpdateAccountInfoWorker_AssistedFactory
          return (T) new UpdateAccountInfoWorker_AssistedFactory() {
            @Override
            public UpdateAccountInfoWorker create(Context context6,
                WorkerParameters workerParams5) {
              return new UpdateAccountInfoWorker(context6, workerParams5, new SyncWorkerComponentBuilder(singletonCImpl), singletonCImpl.providesCoroutineDispatchersProvider.get());
            }
          };

          case 17: // com.geekorum.ttrss.sync.workers.UpdateArticleStatusWorker_AssistedFactory
          return (T) new UpdateArticleStatusWorker_AssistedFactory() {
            @Override
            public UpdateArticleStatusWorker create(Context context7,
                WorkerParameters workerParams6) {
              return new UpdateArticleStatusWorker(context7, workerParams6, new SyncWorkerComponentBuilder(singletonCImpl), singletonCImpl.providesCoroutineDispatchersProvider.get());
            }
          };

          case 18: // android.os.PowerManager
          return (T) AndroidFrameworkModule_ProvidesPowerManagerFactory.providesPowerManager(singletonCImpl.androidFrameworkModule, ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 19: // androidx.datastore.core.DataStore<com.geekorum.ttrss.data.feedsettings.FeedsSettings>
          return (T) FeedsSettingsModule_ProvidesFeedsSettingsDatastoreFactory.providesFeedsSettingsDatastore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 20: // android.security.NetworkSecurityPolicy
          return (T) AndroidFrameworkModule_ProvidesNetSecurityPolicyFactory.providesNetSecurityPolicy(singletonCImpl.androidFrameworkModule);

          case 21: // com.geekorum.ttrss.on_demand_modules.OnDemandModuleNavHostFragment
          return (T) new OnDemandModuleNavHostFragment(AllFeaturesInstalledModule_ProvidesOnDemandModuleManagerFactory.providesOnDemandModuleManager());

          case 22: // android.content.pm.PackageManager
          return (T) AndroidFrameworkModule_ProvidesPackageManagerFactory.providesPackageManager(singletonCImpl.androidFrameworkModule, ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 23: // androidx.datastore.core.DataStore<com.geekorum.ttrss.data.ArticlesSearchHistory>
          return (T) ArticlesSearchHistoryModule_ProvidesArticlesSearchHistoryDatastoreFactory.providesArticlesSearchHistoryDatastore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 24: // androidx.work.WorkManager
          return (T) ManageFeedModule_Companion_ProvideWorkManagerFactory.provideWorkManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 25: // com.geekorum.geekdroid.accounts.AccountSelector
          return (T) AccountSelectorModule_BindsAccountSelectorFactory.bindsAccountSelector(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.providesAccountManagerProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
