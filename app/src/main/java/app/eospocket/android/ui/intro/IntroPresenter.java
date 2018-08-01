package app.eospocket.android.ui.intro;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import io.mithrilcoin.eos.util.Consts;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IntroPresenter extends BasePresenter<IntroView> {

    private EosManager mEosManager;

    public IntroPresenter(IntroView view, EosManager eosManager) {
        super(view);
        this.mEosManager = eosManager;
    }

    public void checkWalletExist() {
        mEosManager.hasWallet(Consts.DEFAULT_WALLET_NAME)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result -> {
                if (result) {
                    mView.startLoginActivity();
                } else {
                    mView.startCreateWalletActivity();
                }
            }, e -> {
                // todo - error
            });
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
