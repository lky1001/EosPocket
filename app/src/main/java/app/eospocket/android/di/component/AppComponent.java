package app.eospocket.android.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import app.eospocket.android.di.ApplicationContext;
import app.eospocket.android.di.module.AppModule;
import dagger.Component;

@Singleton
@Component( modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();
}
