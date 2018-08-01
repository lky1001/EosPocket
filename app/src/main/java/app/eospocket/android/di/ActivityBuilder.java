package app.eospocket.android.di;

import app.eospocket.android.ui.createwallet.CreateWalletActivity;
import app.eospocket.android.ui.intro.IntroActivity;
import app.eospocket.android.ui.intro.IntroActivityModule;
import app.eospocket.android.ui.main.MainActivity;
import app.eospocket.android.ui.main.MainActivityModule;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = IntroActivityModule.class)
    abstract IntroActivity bindIntroActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = CreateWalletActivity.class)
    abstract CreateWalletActivity bindCreateWalletActivity();
}