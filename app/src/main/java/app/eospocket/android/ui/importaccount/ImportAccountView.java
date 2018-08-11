package app.eospocket.android.ui.importaccount;

import app.eospocket.android.common.mvp.IView;
import app.eospocket.android.eos.model.account.EosAccount;

public interface ImportAccountView extends IView {

    void getAccount(String account);

    void noAccount();

    void foundAccount(EosAccount result);

    void successImport();

    void existAccount();
}
