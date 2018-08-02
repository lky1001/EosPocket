package app.eospocket.android.ui.createwallet;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.utils.Encryption;
import app.eospocket.android.utils.KeyStoreUtils;

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
}
