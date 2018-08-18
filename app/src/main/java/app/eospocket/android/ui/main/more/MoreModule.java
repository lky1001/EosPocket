package app.eospocket.android.ui.main.more;

import app.eospocket.android.di.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MoreModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {MoreFragmentModule.class})
    public abstract MoreFragment contributeMoreFragment();
}
