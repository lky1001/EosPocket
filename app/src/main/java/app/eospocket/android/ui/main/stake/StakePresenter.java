package app.eospocket.android.ui.main.stake;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import io.reactivex.Single;

public class StakePresenter extends BasePresenter<StakeView> {

    private EosManager eosManager;
    private RxJavaSchedulers mRxJavaSchedulers;
    private EosAccountRepository mEosAccountRepository;

    public StakePresenter(StakeView view, EosManager eosManager, RxJavaSchedulers rxJavaSchedulers,
                          EosAccountRepository eosAccountRepository) {
        super(view);
        this.eosManager = eosManager;
        this.mRxJavaSchedulers = rxJavaSchedulers;
        this.mEosAccountRepository = eosAccountRepository;
    }

    @Override
    public void onCreate() {
        mEosAccountRepository.getEosAccounts()
                .subscribeOn(mRxJavaSchedulers.getIo())
                .observeOn(mRxJavaSchedulers.getMainThread())
                .subscribe(mView::loadEosAccountListSuccess, mView::loadEosAccountFail);
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
