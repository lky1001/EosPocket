package app.eospocket.android.ui.createwallet;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.utils.EncryptUtils;

public class CreateWalletPresenter extends BasePresenter<CreateWalletView> {

    private EosManager mEosManager;

    private EncryptUtils mEncryptUtils;

    public CreateWalletPresenter(CreateWalletView view, EosManager eosManager, EncryptUtils encryptUtils) {
        super(view);
        this.mEosManager = eosManager;
        this.mEncryptUtils = encryptUtils;
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
