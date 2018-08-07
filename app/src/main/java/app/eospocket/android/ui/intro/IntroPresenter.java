package app.eospocket.android.ui.intro;

import java.util.concurrent.TimeUnit;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.security.AuthManager;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IntroPresenter extends BasePresenter<IntroView> {

    private AuthManager mAuthManager;

    public IntroPresenter(IntroView view, AuthManager authManager) {
        super(view);
        this.mAuthManager = authManager;
    }

    public void initWallet() {
        Single.fromCallable(() -> {
            return true;
        })
        .subscribeOn(Schedulers.io())
                .delay(3, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> {
            mView.startMainActivity();
        }, e -> {

        });
    }

    public void checkWalletExist() {
        Single.fromCallable(() -> mAuthManager.isWalletInitialized())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(result -> {
                if (result) {
                    mView.startLoginActivity();
                } else {
                    mView.startCreateWalletActivity();
                }
            }, e -> {
                // todo - error
            });
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
}
