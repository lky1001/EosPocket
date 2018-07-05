package io.mithrilcoin.eos.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import app.eospocket.android.eos.EosCommanderApp;
import dagger.Component;
import io.mithrilcoin.eos.data.EoscDataManager;
import io.mithrilcoin.eos.data.remote.HostInterceptor;
import io.mithrilcoin.eos.di.ApplicationContext;
import io.mithrilcoin.eos.di.module.AppModule;

/**
 * Created by swapnibble on 2017-08-24.
 */
@Singleton
@Component( modules = AppModule.class)
public interface AppComponent {

    void inject(EosCommanderApp eosCommanderApp);

    @ApplicationContext
    Context context();

    Application application();
    EoscDataManager dataManager();
    HostInterceptor hostInterceptor();
}
