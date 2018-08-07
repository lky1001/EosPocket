package app.eospocket.android.ui.importaccount;

import android.support.annotation.Nullable;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
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
            EosPrivateKey eosPrivateKey = new EosPrivateKey();
            EosPublicKey eosPublicKey = eosPrivateKey.getPublicKey();
            // todo get account by public key
            mEosManager.findAccountByPublicKey(eosPublicKey);
            return "account";
        })
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((result) -> {

        }, e -> {

        });
    }
}
