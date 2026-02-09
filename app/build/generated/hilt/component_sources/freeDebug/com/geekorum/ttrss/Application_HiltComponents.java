package com.geekorum.ttrss;

import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import com.geekorum.ttrss.accounts.AndroidTinyrssAccountManagerModule;
import com.geekorum.ttrss.accounts.AuthenticatorService_GeneratedInjector;
import com.geekorum.ttrss.accounts.HiltWrapper_AuthenticatorActivityModule;
import com.geekorum.ttrss.accounts.HiltWrapper_AuthenticatorServiceModule;
import com.geekorum.ttrss.accounts.LoginActivity_GeneratedInjector;
import com.geekorum.ttrss.accounts.LoginViewModel_HiltModules;
import com.geekorum.ttrss.add_feed.AddFeedInstallerActivity_GeneratedInjector;
import com.geekorum.ttrss.add_feed.AddFeedLauncherActivity_GeneratedInjector;
import com.geekorum.ttrss.app_reviews.AppReviewViewModel_HiltModules;
import com.geekorum.ttrss.article_details.ArticleDetailActivity_GeneratedInjector;
import com.geekorum.ttrss.article_details.ArticleDetailsEntryPoint;
import com.geekorum.ttrss.article_details.ArticleDetailsViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.ActivityViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.ArticleListActivity_GeneratedInjector;
import com.geekorum.ttrss.articles_list.ArticlesListByTagViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.ArticlesListViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.FeedsViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.TagsViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.TtrssAccountViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.magazine.MagazineViewModel_HiltModules;
import com.geekorum.ttrss.articles_list.search.SearchViewModel_HiltModules;
import com.geekorum.ttrss.background_job.BackgroundJobManagerInitializer;
import com.geekorum.ttrss.background_job.BackgroundJobsModule;
import com.geekorum.ttrss.background_job.WorkManagerInitializer;
import com.geekorum.ttrss.core.ActualCoroutineDispatchersModule;
import com.geekorum.ttrss.core.CoreFactoriesModule;
import com.geekorum.ttrss.data.ArticlesDatabaseModule;
import com.geekorum.ttrss.data.ArticlesSearchHistoryModule;
import com.geekorum.ttrss.data.DiskDatabaseModule;
import com.geekorum.ttrss.data.feedsettings.FeedsSettingsModule;
import com.geekorum.ttrss.di.ApplicationComponentEntryPoint;
import com.geekorum.ttrss.di.ExternalsModule;
import com.geekorum.ttrss.di.FreeFlavorApplicationModule;
import com.geekorum.ttrss.di.NetworkModule;
import com.geekorum.ttrss.in_app_update.InAppUpdateViewModel_HiltModules;
import com.geekorum.ttrss.logging.LogcatLoggingModule;
import com.geekorum.ttrss.logging.TimberInitializer;
import com.geekorum.ttrss.logging.TimberModule;
import com.geekorum.ttrss.manage_feeds.EditFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.EditSpecialFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.ManageFeedModule;
import com.geekorum.ttrss.manage_feeds.ManageFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.ManageFeedsActivity_GeneratedInjector;
import com.geekorum.ttrss.manage_feeds.add_feed.AddFeedActivity_GeneratedInjector;
import com.geekorum.ttrss.manage_feeds.add_feed.AddFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedActivity_GeneratedInjector;
import com.geekorum.ttrss.manage_feeds.add_feed.SubscribeToFeedViewModel_HiltModules;
import com.geekorum.ttrss.manage_feeds.workers.SubscribeWorker_HiltModule;
import com.geekorum.ttrss.manage_feeds.workers.UnsubscribeWorker_HiltModule;
import com.geekorum.ttrss.network.TinyrssApiModule;
import com.geekorum.ttrss.on_demand_modules.InstallModuleViewModel_HiltModules;
import com.geekorum.ttrss.on_demand_modules.OnDemandModules;
import com.geekorum.ttrss.providers.PurgeArticlesWorker_HiltModule;
import com.geekorum.ttrss.publish_article.ShareToPublishArticleActivity_GeneratedInjector;
import com.geekorum.ttrss.publish_article.SharingToPublishViewModel_HiltModules;
import com.geekorum.ttrss.session.AccountSelectorModule;
import com.geekorum.ttrss.session.SessionActivityModule;
import com.geekorum.ttrss.settings.ApplicationPreferencesModule;
import com.geekorum.ttrss.settings.SettingsActivity_GeneratedInjector;
import com.geekorum.ttrss.settings.SettingsModule;
import com.geekorum.ttrss.settings.manage_features.InstallFeatureActivity_GeneratedInjector;
import com.geekorum.ttrss.settings.manage_features.ManageFeaturesFragment_GeneratedInjector;
import com.geekorum.ttrss.settings.manage_features.ManageFeaturesViewModel_HiltModules;
import com.geekorum.ttrss.sync.ArticleSyncService_GeneratedInjector;
import com.geekorum.ttrss.sync.HiltWrapper_DatabaseAccessModule;
import com.geekorum.ttrss.sync.HiltWrapper_SyncComponentModule;
import com.geekorum.ttrss.sync.workers.CollectNewArticlesWorker_HiltModule;
import com.geekorum.ttrss.sync.workers.HiltWrapper_FaviKonModule;
import com.geekorum.ttrss.sync.workers.SendTransactionsWorker_HiltModule;
import com.geekorum.ttrss.sync.workers.SyncFeedsIconWorker_HiltModule;
import com.geekorum.ttrss.sync.workers.SyncFeedsWorker_HiltModule;
import com.geekorum.ttrss.sync.workers.UpdateAccountInfoWorker_HiltModule;
import com.geekorum.ttrss.sync.workers.UpdateArticleStatusWorker_HiltModule;
import com.geekorum.ttrss.sync.workers.WorkersModule;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivitySavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.processing.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class Application_HiltComponents {
  private Application_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          AccountSelectorModule.class,
          ActualCoroutineDispatchersModule.class,
          AndroidTinyrssAccountManagerModule.class,
          ApplicationContextModule.class,
          ApplicationPreferencesModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class,
          ArticlesDatabaseModule.class,
          ArticlesSearchHistoryModule.class,
          BackgroundJobsModule.class,
          CollectNewArticlesWorker_HiltModule.class,
          CoreFactoriesModule.class,
          DiskDatabaseModule.class,
          ExternalsModule.class,
          FeedsSettingsModule.class,
          FreeFlavorApplicationModule.class,
          HiltWrapper_AuthenticatorActivityModule.class,
          HiltWrapper_DatabaseAccessModule.class,
          HiltWrapper_FaviKonModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          HiltWrapper_WorkerFactoryModule.class,
          LogcatLoggingModule.class,
          ManageFeedModule.class,
          NetworkModule.class,
          OnDemandModules.class,
          PurgeArticlesWorker_HiltModule.class,
          SendTransactionsWorker_HiltModule.class,
          SubscribeWorker_HiltModule.class,
          SyncFeedsIconWorker_HiltModule.class,
          SyncFeedsWorker_HiltModule.class,
          TimberModule.class,
          TinyrssApiModule.class,
          UnsubscribeWorker_HiltModule.class,
          UpdateAccountInfoWorker_HiltModule.class,
          UpdateArticleStatusWorker_HiltModule.class,
          WorkersModule.class
      }
  )
  @Singleton
  @jakarta.inject.Singleton
  public abstract static class SingletonC implements Application_GeneratedInjector,
      BackgroundJobManagerInitializer.BackgroundJobManagerEntryPoint,
      WorkManagerInitializer.WorkManagerEntryPoint,
      ApplicationComponentEntryPoint,
      TimberInitializer.TimberEntryPoint,
      FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent {
  }

  @Subcomponent(
      modules = {
          HiltWrapper_AuthenticatorServiceModule.class,
          HiltWrapper_SyncComponentModule.class
      }
  )
  @ServiceScoped
  public abstract static class ServiceC implements AuthenticatorService_GeneratedInjector,
      ArticleSyncService_GeneratedInjector,
      ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ActivityViewModel_HiltModules.KeyModule.class,
          AddFeedViewModel_HiltModules.KeyModule.class,
          AppReviewViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          ArticleDetailsViewModel_HiltModules.KeyModule.class,
          ArticlesListByTagViewModel_HiltModules.KeyModule.class,
          ArticlesListViewModel_HiltModules.KeyModule.class,
          EditFeedViewModel_HiltModules.KeyModule.class,
          EditSpecialFeedViewModel_HiltModules.KeyModule.class,
          FeedsViewModel_HiltModules.KeyModule.class,
          ForceNightModeViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HiltWrapper_ActivitySavedStateHandleModule.class,
          InAppUpdateViewModel_HiltModules.KeyModule.class,
          InstallModuleViewModel_HiltModules.KeyModule.class,
          LoginViewModel_HiltModules.KeyModule.class,
          MagazineViewModel_HiltModules.KeyModule.class,
          ManageFeaturesViewModel_HiltModules.KeyModule.class,
          ManageFeedViewModel_HiltModules.KeyModule.class,
          SearchViewModel_HiltModules.KeyModule.class,
          SessionActivityModule.class,
          SharingToPublishViewModel_HiltModules.KeyModule.class,
          SubscribeToFeedViewModel_HiltModules.KeyModule.class,
          TagsViewModel_HiltModules.KeyModule.class,
          TtrssAccountViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class,
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
          SettingsModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements MainActivity_GeneratedInjector,
      SingleActivity_GeneratedInjector,
      LoginActivity_GeneratedInjector,
      AddFeedInstallerActivity_GeneratedInjector,
      AddFeedLauncherActivity_GeneratedInjector,
      ArticleDetailActivity_GeneratedInjector,
      ArticleDetailsEntryPoint,
      ArticleListActivity_GeneratedInjector,
      ManageFeedsActivity_GeneratedInjector,
      AddFeedActivity_GeneratedInjector,
      SubscribeToFeedActivity_GeneratedInjector,
      ShareToPublishArticleActivity_GeneratedInjector,
      SettingsActivity_GeneratedInjector,
      InstallFeatureActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          ActivityViewModel_HiltModules.BindsModule.class,
          AddFeedViewModel_HiltModules.BindsModule.class,
          AppReviewViewModel_HiltModules.BindsModule.class,
          ArticleDetailsViewModel_HiltModules.BindsModule.class,
          ArticlesListByTagViewModel_HiltModules.BindsModule.class,
          ArticlesListViewModel_HiltModules.BindsModule.class,
          EditFeedViewModel_HiltModules.BindsModule.class,
          EditSpecialFeedViewModel_HiltModules.BindsModule.class,
          FeedsViewModel_HiltModules.BindsModule.class,
          ForceNightModeViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          InAppUpdateViewModel_HiltModules.BindsModule.class,
          InstallModuleViewModel_HiltModules.BindsModule.class,
          LoginViewModel_HiltModules.BindsModule.class,
          MagazineViewModel_HiltModules.BindsModule.class,
          ManageFeaturesViewModel_HiltModules.BindsModule.class,
          ManageFeedViewModel_HiltModules.BindsModule.class,
          SearchViewModel_HiltModules.BindsModule.class,
          SharingToPublishViewModel_HiltModules.BindsModule.class,
          SubscribeToFeedViewModel_HiltModules.BindsModule.class,
          TagsViewModel_HiltModules.BindsModule.class,
          TtrssAccountViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements ManageFeaturesFragment_GeneratedInjector,
      FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
