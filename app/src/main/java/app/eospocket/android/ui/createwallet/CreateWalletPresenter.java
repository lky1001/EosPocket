package app.eospocket.android.ui.createwallet;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;

public class CreateWalletPresenter extends BasePresenter<CreateWalletView> {

    private EosManager mEosManager;

    public CreateWalletPresenter(CreateWalletView view, EosManager eosManager) {
        super(view);
        this.mEosManager = eosManager;
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
