package app.eospocket.android.ui.login;

import app.eospocket.android.eos.EosManager;
import app.eospocket.android.security.AuthManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginActivityModule {

    @Binds
    public abstract LoginView view(LoginActivity loginActivity);

    @Provides
    static LoginPresenter provideLoginPresenter(LoginView loginView,
            EosManager eosManager, AuthManager authManager) {
        return new LoginPresenter(loginView, eosManager, authManager);
    }
}
