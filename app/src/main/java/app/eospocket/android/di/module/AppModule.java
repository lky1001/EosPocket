package app.eospocket.android.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import app.eospocket.android.eos.EosManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static EosManager provideEosManager() {
        return new EosManager();
    }
}
