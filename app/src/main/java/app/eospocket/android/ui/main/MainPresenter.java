package app.eospocket.android.ui.main;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.request.AccountRequest;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {

    private EosManager mEosManager;

    public MainPresenter(MainView view, EosManager eosManager) {
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

    public void getAccount(String accountName) {
        Single.fromCallable(() -> {
            AccountRequest request = new AccountRequest();
            request.accountName = accountName;
            return request;
        })
        .flatMap((request) -> mEosManager.findAccountByName(request))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(account -> {

        }, e -> {
            e.printStackTrace();
        });
    }
}
