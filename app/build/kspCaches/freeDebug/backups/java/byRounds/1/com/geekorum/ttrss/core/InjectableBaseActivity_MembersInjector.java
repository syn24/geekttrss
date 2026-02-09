package com.geekorum.ttrss.core;

import androidx.fragment.app.FragmentFactory;
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
public final class InjectableBaseActivity_MembersInjector implements MembersInjector<InjectableBaseActivity> {
  private final Provider<FragmentFactory> daggerDelegateFragmentFactoryProvider;

  private InjectableBaseActivity_MembersInjector(
      Provider<FragmentFactory> daggerDelegateFragmentFactoryProvider) {
    this.daggerDelegateFragmentFactoryProvider = daggerDelegateFragmentFactoryProvider;
  }

  @Override
  public void injectMembers(InjectableBaseActivity instance) {
    injectDaggerDelegateFragmentFactory(instance, daggerDelegateFragmentFactoryProvider.get());
  }

  public static MembersInjector<InjectableBaseActivity> create(
      Provider<FragmentFactory> daggerDelegateFragmentFactoryProvider) {
    return new InjectableBaseActivity_MembersInjector(daggerDelegateFragmentFactoryProvider);
  }

  @InjectedFieldSignature("com.geekorum.ttrss.core.InjectableBaseActivity.daggerDelegateFragmentFactory")
  public static void injectDaggerDelegateFragmentFactory(InjectableBaseActivity instance,
      FragmentFactory daggerDelegateFragmentFactory) {
    instance.daggerDelegateFragmentFactory = daggerDelegateFragmentFactory;
  }
}
