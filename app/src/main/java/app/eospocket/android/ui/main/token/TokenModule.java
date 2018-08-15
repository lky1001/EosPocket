package app.eospocket.android.ui.main.token;

import app.eospocket.android.di.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TokenModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {TokenFragmentModule.class})
    public abstract TokenFragment contributeTokenFragment();
}
