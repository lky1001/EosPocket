package app.eospocket.android.ui.createwallet;

import app.eospocket.android.eos.EosManager;
import app.eospocket.android.utils.EncryptUtils;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CreateWalletActivityModule {

    @Binds
    public abstract CreateWalletView view(CreateWalletActivity createWalletActivity);

    @Provides
    static CreateWalletPresenter provideCreateWalletPresenter(CreateWalletView createWalletView,
            EosManager eosManager, EncryptUtils encryptUtils) {
        return new CreateWalletPresenter(createWalletView, eosManager, encryptUtils);
    }
}
