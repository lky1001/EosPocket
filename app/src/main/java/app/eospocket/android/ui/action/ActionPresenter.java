package app.eospocket.android.ui.action;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;

public class ActionPresenter extends BasePresenter<ActionView> {

    private EosManager mEosManager;

    public ActionPresenter(ActionView view, EosManager eosManager) {
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
