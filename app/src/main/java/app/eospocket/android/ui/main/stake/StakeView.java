package app.eospocket.android.ui.main.stake;

import java.util.List;

import app.eospocket.android.common.mvp.IView;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.wallet.db.model.EosAccountModel;

public interface StakeView extends IView {
    void loadEosAccountSuccess(EosAccount eosAccount);
    void loadEosAccountFail(Throwable t);

    void loadEosAccountListSuccess(List<EosAccountModel> eosAccountModelList);
    void loadEosAccountListFail(Throwable t);
}
