package app.eospocket.android.ui.createwallet;

import android.os.Build;
import android.security.keystore.UserNotAuthenticatedException;
import android.util.Log;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.security.AuthManager;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CreateWalletPresenter extends BasePresenter<CreateWalletView> {

    private EosManager mEosManager;

    private AuthManager mAuthManager;

    public CreateWalletPresenter(CreateWalletView view, EosManager eosManager, AuthManager authManager) {
        super(view);
        this.mEosManager = eosManager;
        this.mAuthManager = authManager;
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

    public void createWallet(String password) {
        Single.fromCallable(() -> {
            mAuthManager.setPinCode(password);

            Log.i("test", mAuthManager.getPinCode());

            return true;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
            if (result) {
                mView.successCreateWallet();
            } else {
                mView.failCreateWallet();
            }
        }, (e) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (e instanceof UserNotAuthenticatedException) {

                }
            }

            if (e instanceof IllegalStateException) {
                mView.existWallet();
            } else {
                mView.failCreateWallet();
            }
        });
    }
}
