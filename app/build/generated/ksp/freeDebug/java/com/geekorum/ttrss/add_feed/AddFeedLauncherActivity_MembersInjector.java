package com.geekorum.ttrss.add_feed;

import androidx.fragment.app.FragmentFactory;
import com.geekorum.ttrss.core.InjectableBaseActivity_MembersInjector;
import com.geekorum.ttrss.on_demand_modules.OnDemandModuleManager;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class AddFeedLauncherActivity_MembersInjector implements MembersInjector<AddFeedLauncherActivity> {
  private final Provider<FragmentFactory> daggerDelegateFragmentFactoryProvider;

  private final Provider<OnDemandModuleManager> moduleManagerProvider;

  private AddFeedLauncherActivity_MembersInjector(
      Provider<FragmentFactory> daggerDelegateFragmentFactoryProvider,
      Provider<OnDemandModuleManager> moduleManagerProvider) {
    this.daggerDelegateFragmentFactoryProvider = daggerDelegateFragmentFactoryProvider;
    this.moduleManagerProvider = moduleManagerProvider;
  }

  @Override
  public void injectMembers(AddFeedLauncherActivity instance) {
    InjectableBaseActivity_MembersInjector.injectDaggerDelegateFragmentFactory(instance, daggerDelegateFragmentFactoryProvider.get());
    injectModuleManager(instance, moduleManagerProvider.get());
  }

  public static MembersInjector<AddFeedLauncherActivity> create(
      Provider<FragmentFactory> daggerDelegateFragmentFactoryProvider,
      Provider<OnDemandModuleManager> moduleManagerProvider) {
    return new AddFeedLauncherActivity_MembersInjector(daggerDelegateFragmentFactoryProvider, moduleManagerProvider);
  }

  @InjectedFieldSignature("com.geekorum.ttrss.add_feed.AddFeedLauncherActivity.moduleManager")
  public static void injectModuleManager(AddFeedLauncherActivity instance,
      OnDemandModuleManager moduleManager) {
    instance.moduleManager = moduleManager;
  }
}
