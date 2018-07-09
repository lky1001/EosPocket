package app.eospocket.android.ui.intro;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;

public class IntroPresenter extends BasePresenter<IntroView> {

    private EosManager mEosManager;

    public IntroPresenter(IntroView view, EosManager eosManager) {
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
