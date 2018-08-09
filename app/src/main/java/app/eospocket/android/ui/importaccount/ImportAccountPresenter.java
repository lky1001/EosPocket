package app.eospocket.android.ui.importaccount;

import android.support.annotation.Nullable;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.eos.request.KeyAccountsRequest;
import io.mithrilcoin.eos.crypto.ec.EosPrivateKey;
import io.mithrilcoin.eos.crypto.ec.EosPublicKey;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImportAccountPresenter extends BasePresenter<ImportAccountView> {

    private EosManager mEosManager;

    public ImportAccountPresenter(ImportAccountView view, EosManager eosManager) {
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

    public void findAccount(@Nullable String privateKey) {
        Single.fromCallable(() -> {
            EosPrivateKey eosPrivateKey = new EosPrivateKey(privateKey);
            EosPublicKey eosPublicKey = eosPrivateKey.getPublicKey();
            return eosPublicKey.toString();
        })
        .flatMap(publicKey -> {
            KeyAccountsRequest request = new KeyAccountsRequest();
            request.publicKey = publicKey;
            return mEosManager.findAccountByPublicKey(request);
        })
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
            if (result != null && !result.accounts.isEmpty()) {
                mView.getAccount(result.accounts.get(0));
            }
        }, e -> {
            e.printStackTrace();
        });
    }

    public void findAccountName(String accountName) {
        Single.fromCallable(() -> {
            AccountRequest request = new AccountRequest();
            request.accountName = accountName;

            return request;
        })
        .flatMap(request -> mEosManager.findAccountByName(request))
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
            mView.foundAccount(result);
        }, e -> {
            e.printStackTrace();
            mView.noAccount();
        });
    }
}
