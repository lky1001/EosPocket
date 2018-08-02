package app.eospocket.android.ui.createwallet;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.utils.Encryption;
import app.eospocket.android.utils.KeyStoreUtils;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CreateWalletActivityModule {

    @Binds
    public abstract CreateWalletView view(CreateWalletActivity createWalletActivity);

    @Provides
    static CreateWalletPresenter provideCreateWalletPresenter(CreateWalletView createWalletView,
            EosManager eosManager, KeyStoreUtils keyStoreUtils, Encryption encryption,
            CustomPreference customPreference) {
        return new CreateWalletPresenter(createWalletView, eosManager, keyStoreUtils, encryption, customPreference);
    }
}
