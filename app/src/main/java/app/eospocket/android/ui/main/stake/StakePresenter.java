package app.eospocket.android.ui.main.stake;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.request.AccountRequest;
import io.reactivex.Single;

public class StakePresenter extends BasePresenter<StakeView> {

    private EosManager eosManager;
    private RxJavaSchedulers mRxJavaSchedulers;

    public StakePresenter(StakeView view, EosManager eosManager, RxJavaSchedulers rxJavaSchedulers) {
        super(view);
        this.eosManager = eosManager;
        this.mRxJavaSchedulers = rxJavaSchedulers;
    }

    @Override
    public void onCreate() {

    }

    public void loadEosAccount(String accountName) {
        Single.fromCallable(() -> {
            AccountRequest request = new AccountRequest();
            request.accountName = accountName;
            return request;
        })
                .flatMap((request) -> eosManager.findAccountByName(request))
                .subscribeOn(mRxJavaSchedulers.getIo())
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe(mView::loadEosAccountSuccess, mView::loadEosAccountFail);
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
