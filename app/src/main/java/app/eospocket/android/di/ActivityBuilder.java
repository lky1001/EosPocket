package app.eospocket.android.di;

import app.eospocket.android.ui.intro.IntroActivity;
import app.eospocket.android.ui.intro.IntroActivityModule;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = IntroActivityModule.class)
    abstract IntroActivity bindIntroActivity();

}