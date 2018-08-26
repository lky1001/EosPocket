package app.eospocket.android.ui.importaccount;

import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.security.keystore.KeyStore;
import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.PocketAppManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ImportAccountActivityModule {

    @Binds
    public abstract ImportAccountView view(ImportAccountActivity importAccountActivity);

    @Provides
    static ImportAccountPresenter provideImportAccountPresenter(ImportAccountView importAccountView,
            EosManager eosManager, EncryptUtil encryptUtil, KeyStore keyStore,
            PocketAppManager pocketAppManager, RxJavaSchedulers rxJavaSchedulers) {
        return new ImportAccountPresenter(importAccountView, eosManager, encryptUtil, keyStore,
                pocketAppManager, rxJavaSchedulers);
    }
}
