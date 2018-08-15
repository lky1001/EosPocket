package app.eospocket.android.ui.main.setting;

import app.eospocket.android.di.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SettingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {SettingFragmentModule.class})
    public abstract SettingFragment contributeSettingFragment();
}
