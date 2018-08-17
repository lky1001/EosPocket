package app.eospocket.android.ui.main.action;

import app.eospocket.android.di.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {ActionFragmentModule.class})
    public abstract ActionFragment contributeActionFragment();
}
