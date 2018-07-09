package app.eospocket.android.common;

import android.content.Context;
import android.support.multidex.MultiDex;

import javax.inject.Inject;

import app.eospocket.android.di.component.DaggerAppComponent;
import app.eospocket.android.eos.EosManager;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class EosApplication extends DaggerApplication {

    @Inject
    EosManager eosManager;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
