package app.eospocket.android.ui.main.stake;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.wallet.LoginAccountManager;
import io.reactivex.Single;

public class StakePresenter extends BasePresenter<StakeView> {

    private EosManager eosManager;
    private RxJavaSchedulers rxJavaSchedulers;
    private LoginAccountManager loginAccountManager;

    public StakePresenter(StakeView view, EosManager eosManager, RxJavaSchedulers rxJavaSchedulers,
                          LoginAccountManager loginAccountManager) {
        super(view);
        this.eosManager = eosManager;
        this.rxJavaSchedulers = rxJavaSchedulers;
        this.loginAccountManager = loginAccountManager;
    }

    @Override
    public void onCreate() {
        loginAccountManager
                .getChangeAccount()
                .subscribe(account -> loadEosAccount(account.getName()));
    }

    public void loadEosAccount(String accountName) {
        Single.fromCallable(() -> {
            AccountRequest request = new AccountRequest();
            request.accountName = accountName;
            return request;
        })
                .flatMap((request) -> eosManager.findAccountByName(request))
                .subscribeOn(rxJavaSchedulers.getIo())
                .observeOn(rxJavaSchedulers.getMainThread())
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
