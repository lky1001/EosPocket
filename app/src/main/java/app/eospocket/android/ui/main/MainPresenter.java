package app.eospocket.android.ui.main;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.wallet.repository.EosAccountRepository;

public class MainPresenter extends BasePresenter<MainView> {

    private EosAccountRepository eosAccountRepository;
    private RxJavaSchedulers rxJavaSchedulers;

    public MainPresenter(MainView view, RxJavaSchedulers rxJavaSchedulers, EosAccountRepository eosAccountRepository) {
        super(view);
        this.rxJavaSchedulers = rxJavaSchedulers;
        this.eosAccountRepository = eosAccountRepository;
    }

    @Override
    public void onCreate() {
        eosAccountRepository.getEosAccounts()
                .subscribeOn(rxJavaSchedulers.getIo())
                .observeOn(rxJavaSchedulers.getMainThread())
                .subscribe(mView::loadEosAccountListSuccess, mView::loadEosAccountListFail);
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
