package app.eospocket.android.ui.main.stake;

import app.eospocket.android.di.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StakeModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {StakeFragmentModule.class})
    public abstract StakeFragment contributeStakeFragment();
}
