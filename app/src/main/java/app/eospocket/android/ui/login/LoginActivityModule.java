package app.eospocket.android.ui.login;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.ui.createwallet.CreateWalletPresenter;
import app.eospocket.android.utils.Encryption;
import app.eospocket.android.utils.KeyStoreUtils;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginActivityModule {

    @Binds
    public abstract LoginView view(LoginActivity loginActivity);

    @Provides
    static LoginPresenter provideLoginPresenter(LoginView loginView,
            EosManager eosManager, KeyStoreUtils keyStoreUtils, Encryption encryption,
            CustomPreference customPreference) {
        return new LoginPresenter(loginView, eosManager, keyStoreUtils, encryption, customPreference);
    }
}
