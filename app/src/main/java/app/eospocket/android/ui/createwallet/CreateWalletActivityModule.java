package app.eospocket.android.ui.createwallet;

import app.eospocket.android.eos.EosManager;
import app.eospocket.android.security.AuthManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CreateWalletActivityModule {

    @Binds
    public abstract CreateWalletView view(CreateWalletActivity createWalletActivity);

    @Provides
    static CreateWalletPresenter provideCreateWalletPresenter(CreateWalletView createWalletView,
            EosManager eosManager, AuthManager authManager) {
        return new CreateWalletPresenter(createWalletView, eosManager, authManager);
    }
}
