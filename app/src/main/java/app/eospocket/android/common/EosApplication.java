package app.eospocket.android.common;

import android.content.Context;
import android.support.multidex.MultiDex;

import javax.inject.Inject;

import app.eospocket.android.di.DaggerAppComponent;
import app.eospocket.android.eos.EosManager;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.mithrilcoin.eos.app.PRNGFixes;

public class EosApplication extends DaggerApplication {

    @Inject
    EosManager eosManager;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // https://android-developers.googleblog.com/2013/08/some-securerandom-thoughts.html
        // https://github.com/playerone-id/EosCommander/blob/master/app/src/main/java/io/plactal/eoscommander/app/EosCommanderApp.java
        PRNGFixes.apply();

        MultiDex.install(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .application(this)
                .build();
    }
}
