package app.eospocket.android.ui.main.stake;

import app.eospocket.android.common.mvp.IView;
import app.eospocket.android.eos.model.account.EosAccount;

public interface StakeView extends IView {
    void loadEosAccountSuccess(EosAccount eosAccount);
    void loadEosAccountFail(Throwable t);
    void onRefresh();
}
