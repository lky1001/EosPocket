package app.eospocket.android.ui.main.stake;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.request.AccountRequest;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class StakePresenter extends BasePresenter<StakeView> {

    private EosManager eosManager;
    private Scheduler processScheduler;
    private Scheduler observerScheduler;

    public StakePresenter(StakeView view, EosManager eosManager, Scheduler processSchedulers, Scheduler observerScheduler) {
        super(view);
        this.eosManager = eosManager;
        this.processScheduler = processSchedulers;
        this.observerScheduler = observerScheduler;
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
                .subscribeOn(processScheduler)
                .observeOn(observerScheduler)
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
