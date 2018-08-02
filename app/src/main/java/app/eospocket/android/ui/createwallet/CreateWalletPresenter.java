package app.eospocket.android.ui.createwallet;

import android.text.TextUtils;
import android.util.Log;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.utils.Encryption;
import app.eospocket.android.utils.KeyStoreUtils;
import io.mithrilcoin.eos.util.Consts;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreateWalletPresenter extends BasePresenter<CreateWalletView> {

    private EosManager mEosManager;

    private KeyStoreUtils mKeyStoreUtils;

    private Encryption mEncryption;

    private CustomPreference mCustomPreference;

    public CreateWalletPresenter(CreateWalletView view, EosManager eosManager, KeyStoreUtils keyStoreUtils,
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

    public void createWallet(String password) {
        mEosManager.createWallet(Consts.DEFAULT_WALLET_NAME)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pw -> {
                    if (!TextUtils.isEmpty(pw)) {
                        String encryptPassword = mEncryption.encrypt(pw, password);
                        String enc = mKeyStoreUtils.encryptString(encryptPassword, Constants.KEYSTORE_ALIAS);

                        mCustomPreference.saveEosWallet(enc);
                    } else {
                        // todo - error
                    }
                }, (e) -> {
                    if (e instanceof IllegalStateException) {
                        // todo - wallet exist
                    } else {
                        // todo - error msg
                    }
                });
    }
}
