package app.eospocket.android.ui.main;

import java.util.List;

import app.eospocket.android.common.mvp.IView;
import app.eospocket.android.wallet.db.model.EosAccountModel;

public interface MainView extends IView {
    void loadEosAccountListSuccess(List<EosAccountModel> eosAccountModelList);
    void loadEosAccountListFail(Throwable t);
}
