package app.eospocket.android.ui.login;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.utils.Encryption;
import app.eospocket.android.utils.KeyStoreUtils;
import io.mithrilcoin.eos.util.Consts;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {

    private EosManager mEosManager;

    private KeyStoreUtils mKeyStoreUtils;

    private Encryption mEncryption;

    private CustomPreference mCustomPreference;

    public LoginPresenter(LoginView view, EosManager eosManager, KeyStoreUtils keyStoreUtils,
            Encryption encryption, CustomPreference customPreference) {
        super(view);
        this.mEosManager = eosManager;
        this.mKeyStoreUtils = keyStoreUtils;
        this.mEncryption = encryption;
        this.mCustomPreference = customPreference;
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
        String enc = mCustomPreference.loadEosWallet();
        String encryptPassword = mKeyStoreUtils.decryptString(enc, Constants.KEYSTORE_ALIAS);
        String pw = mEncryption.decrypt(encryptPassword, password);

        mEosManager.unlock(Consts.DEFAULT_WALLET_NAME, pw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((result) -> {
                    if (result == EosManager.SUCCESS) {
                        mView.successLogin();
                    } else {
                        mView.failLogin();
                    }
                }, e -> {
                    mView.failLogin();
                });
    }
}
