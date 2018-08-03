package app.eospocket.android.ui.login;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginActivityModule {

    @Binds
    public abstract LoginView view(LoginActivity loginActivity);

    @Provides
    static LoginPresenter provideLoginPresenter(LoginView loginView) {
        return new LoginPresenter(loginView);
    }
}
