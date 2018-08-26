package app.eospocket.android.ui.intro;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.security.AuthManager;
import io.reactivex.Single;

public class IntroPresenter extends BasePresenter<IntroView> {

    public static final int ERROR = 0;
    public static final int PINCODE_SETTING = 1;
    public static final int LOGIN = 2;
    public static final int MAIN = 3;

    private AuthManager mAuthManager;
    private RxJavaSchedulers mRxJavaSchedulers;
    private boolean usePinCode;

    public IntroPresenter(IntroView view, AuthManager authManager, RxJavaSchedulers rxJavaSchedulers) {
        super(view);
        this.mAuthManager = authManager;
        this.mRxJavaSchedulers = rxJavaSchedulers;
    }

    public void initWallet() {
        Single.fromCallable(() -> {
            if (mAuthManager.getUsePinCode()) {
                if (!TextUtils.isEmpty(mAuthManager.getPinCode())) {
                    return LOGIN;
                } else {
                    return PINCODE_SETTING;
                }
            } else {
                return MAIN;
            }
        })
        .subscribeOn(mRxJavaSchedulers.getIo())
        .delay(3, TimeUnit.SECONDS)
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(result -> {
            if (result == PINCODE_SETTING) {
                mView.initPinCode();
            } else if (result == LOGIN) {
                mView.login();
            } else if (result == MAIN) {
                mView.startMainActivity();
            } else {
                // todo error
            }
        }, e -> {

        });
    }

    public void checkWalletExist() {
        Single.fromCallable(() -> mAuthManager.isWalletInitialized())
            .subscribeOn(mRxJavaSchedulers.getIo())
            .observeOn(mRxJavaSchedulers.getMainThread())
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

    public String getPinCode() {
        return mAuthManager.getPinCode();
    }

    public void createWallet(boolean usePinCode, String pinCode) {
        mAuthManager.setUsePinCode(usePinCode);
        mAuthManager.setPinCode(pinCode);
        mAuthManager.walletInitialized();
    }
}
