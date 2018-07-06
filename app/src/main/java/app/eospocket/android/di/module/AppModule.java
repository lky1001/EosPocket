package app.eospocket.android.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import app.eospocket.android.di.ApplicationContext;
import app.eospocket.android.eos.EosManager;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application mApp;

    public AppModule( Application application) { mApp = application;}

    @Provides
    @Singleton
    Application provideApp() { return mApp; }

    @Provides
    @ApplicationContext
    Context provideAppContext() { return mApp; }

    @Provides
    @Singleton
    EosManager provideEosManager() {
        return new EosManager();
    }
}
