package app.eospocket.android.ui.createwallet;

import app.eospocket.android.common.mvp.IView;

public interface CreateWalletView extends IView {

    void successCreateWallet();

    void existWallet();

    void failCreateWallet();
}
