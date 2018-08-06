package app.eospocket.android.ui.login;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.security.AuthManager;

public class LoginPresenter extends BasePresenter<LoginView> {

    private EosManager mEosManager;

    private AuthManager mAuthManager;

    public LoginPresenter(LoginView view, EosManager eosManager, AuthManager authManager) {
        super(view);
        this.mEosManager = eosManager;
        this.mAuthManager = authManager;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void doLogin(String password) {

    }
}
