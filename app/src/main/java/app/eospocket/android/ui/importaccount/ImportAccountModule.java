package app.eospocket.android.ui.importaccount;

import app.eospocket.android.eos.EosManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ImportAccountModule {

    @Binds
    public abstract ImportAccountView view(ImportAccountActivity importAccountActivity);

    @Provides
    static ImportAccountPresenter provideImportAccountPresenter(ImportAccountView importAccountView,
            EosManager eosManager) {
        return new ImportAccountPresenter(importAccountView, eosManager);
    }
}
