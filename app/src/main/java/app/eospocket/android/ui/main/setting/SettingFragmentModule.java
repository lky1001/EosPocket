package app.eospocket.android.ui.main.setting;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SettingFragmentModule {

    @Binds
    public abstract SettingView view(SettingFragment settingFragment);

    @Provides
    static SettingPresenter provideSettingPresenter(SettingView settingView) {
        return new SettingPresenter(settingView);
    }
}
