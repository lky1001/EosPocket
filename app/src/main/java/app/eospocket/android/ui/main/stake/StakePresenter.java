package app.eospocket.android.ui.main.stake;

import android.support.annotation.NonNull;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.eosaction.DelegateEos;
import app.eospocket.android.eos.eosaction.UnDelegateEos;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.utils.Utils;
import app.eospocket.android.wallet.LoginAccountManager;
import app.eospocket.android.wallet.PocketAppManager;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class StakePresenter extends BasePresenter<StakeView> {

    private EosManager eosManager;
    private RxJavaSchedulers rxJavaSchedulers;
    private LoginAccountManager loginAccountManager;
    private PocketAppManager pocketAppManager;
    private EosAccountRepository eosAccountRepository;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public StakePresenter(StakeView view, EosManager eosManager, RxJavaSchedulers rxJavaSchedulers,
                          LoginAccountManager loginAccountManager, PocketAppManager pocketAppManager,
                          EosAccountRepository eosAccountRepository) {
        super(view);
        this.eosManager = eosManager;
        this.rxJavaSchedulers = rxJavaSchedulers;
        this.loginAccountManager = loginAccountManager;
        this.pocketAppManager = pocketAppManager;
        this.eosAccountRepository = eosAccountRepository;
    }

    @Override
    public void onCreate() {
        Disposable d = loginAccountManager
                .getChangeAccount()
                .subscribe(account -> loadEosAccount(account.getName()));

        compositeDisposable.add(d);
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

    public void stakeEos(int accountId, @NonNull String pw, @NonNull String to, double cpuAmount, double netAmount, int isTransfer) {
        this.eosAccountRepository.findOneById(accountId)
                .flatMap(account -> {

                    DelegateEos delegateEos = new DelegateEos(
                            new TypeAccountName(account.getName()),
                            new TypeAccountName(to),
                            Utils.formatBalanceWithEOSSymbol(cpuAmount),
                            Utils.formatBalanceWithEOSSymbol(netAmount),
                            isTransfer
                    );

                    //TODO "@active" -> "@" + account.getPermission();
                    return this.eosManager.signedEosAction(delegateEos, account.getName() + "@active");
                })
                .flatMap(signedTransaction -> {
                    return this.pocketAppManager.signAndPackTransaction(accountId, pw, signedTransaction);
                })
                .flatMap(packedTransaction -> {
                    return this.eosManager.pushTransactionRetJson(packedTransaction);
                })
                .subscribeOn(rxJavaSchedulers.getIo())
                .observeOn(rxJavaSchedulers.getMainThread())
                .subscribe(jsonObject -> {
                    mView.onRefresh();
                }, e -> {
                    if (e instanceof IllegalAccessException) {
                        // todo - password error
                    }
                });
    }

    public void unStakeEos(int accountId, @NonNull String pw, @NonNull String to, double cpuAmount, double netAmount) {
        this.eosAccountRepository.findOneById(accountId)
                .flatMap(account -> {
                    UnDelegateEos unDelegateEos = new UnDelegateEos(
                           new TypeAccountName(account.getName()),
                           new TypeAccountName(to),
                           Utils.formatBalanceWithEOSSymbol(cpuAmount),
                           Utils.formatBalanceWithEOSSymbol(netAmount)
                    );
                    return this.eosManager.signedEosAction(unDelegateEos, account.getName() + "@active");
                })
                .flatMap(signedTransaction -> {
                    return this.pocketAppManager.signAndPackTransaction(accountId, pw, signedTransaction);
                })
                .flatMap(packedTransaction -> {
                    return this.eosManager.pushTransactionRetJson(packedTransaction);
                })
                .subscribeOn(rxJavaSchedulers.getIo())
                .observeOn(rxJavaSchedulers.getMainThread())
                .subscribe(jsonObject -> {
                    mView.onRefresh();
                }, e -> {
                    if (e instanceof IllegalAccessException) {
                        // todo - password error
                    }
                });
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }
}
