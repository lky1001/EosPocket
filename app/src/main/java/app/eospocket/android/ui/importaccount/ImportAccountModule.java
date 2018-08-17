package app.eospocket.android.ui.importaccount;

import app.eospocket.android.eos.EosManager;
import app.eospocket.android.security.keystore.KeyStore;
import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.PocketAppManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public abstract class ImportAccountModule {

    @Binds
    public abstract ImportAccountView view(ImportAccountActivity importAccountActivity);

    @Provides
    static ImportAccountPresenter provideImportAccountPresenter(ImportAccountView importAccountView,
            EosManager eosManager, EncryptUtil encryptUtil, KeyStore keyStore,
            PocketAppManager pocketAppManager) {
        return new ImportAccountPresenter(importAccountView, eosManager, encryptUtil, keyStore,
                pocketAppManager, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
