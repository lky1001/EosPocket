package app.eospocket.android.ui.importaccount;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Calendar;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.model.AccountList;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.eos.request.KeyAccountsRequest;
import app.eospocket.android.security.keystore.KeyStore;
import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.PocketAppManager;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import io.mithrilcoin.eos.crypto.ec.EosPrivateKey;
import io.mithrilcoin.eos.crypto.ec.EosPublicKey;
import io.reactivex.Single;

public class ImportAccountPresenter extends BasePresenter<ImportAccountView> {

    private static final int IMPORT_ACCOUNT_SUCCESS = 0;
    private static final int IMPORT_ACCOUNT_EXIST = -1;

    private EosManager mEosManager;

    private EncryptUtil mEncryptUtil;

    private KeyStore mKeyStore;

    private PocketAppManager mPocketAppManager;
    private RxJavaSchedulers mRxJavaSchedulers;

    public ImportAccountPresenter(ImportAccountView view, EosManager eosManager, EncryptUtil encryptUtil,
            KeyStore keyStore, PocketAppManager pocketAppManager, RxJavaSchedulers rxJavaSchedulers) {
        super(view);

        this.mEosManager = eosManager;
        this.mEncryptUtil = encryptUtil;
        this.mKeyStore = keyStore;
        this.mPocketAppManager = pocketAppManager;
        this.mRxJavaSchedulers = rxJavaSchedulers;
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

    public void findAccountName(String accountName) {
        Single.fromCallable(() -> {
            AccountRequest request = new AccountRequest();
            request.accountName = accountName;

            return request;
        })
        .flatMap(request -> mEosManager.findAccountByName(request))
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(result -> {
            mView.foundAccount(result);
        }, e -> {
            e.printStackTrace();
            mView.noAccount();
        });
    }

    public void importAccount(@NonNull String accountName) {
        importAccount(accountName, null, null);
    }

    public void importAccount(@NonNull String accountName, @Nullable String privateKey, @Nullable String password) {
        Single.fromCallable(() -> {
            if (!TextUtils.isEmpty(privateKey)) {
                EosPrivateKey eosPrivateKey = new EosPrivateKey(privateKey);
                EosPublicKey eosPublicKey = eosPrivateKey.getPublicKey();
                return eosPublicKey.toString();
            }

            return "";
        })
        .flatMap(publicKey -> {
            if (!TextUtils.isEmpty(publicKey)) {
                KeyAccountsRequest request = new KeyAccountsRequest();
                request.publicKey = publicKey;
                return mEosManager.findAccountByPublicKey(request);
            }

            return Single.fromCallable(() -> new AccountList());
        })
        .map(accountList -> {
            if (accountList.accounts != null) {
                for (String account : accountList.accounts) {
                    if (accountName.equals(account)) {
                        return true;
                    }
                }

                return false;
            }

            return true;
        })
        .flatMap(matchPrivateKey -> {
            // private key matched account name
            if (matchPrivateKey) {
                return mPocketAppManager.findAccount(accountName);
            }

            throw new IllegalAccessException("Account key and private key does not match.");
        })
        .map(accounts -> {
            if (accounts.isEmpty()) {
                EosAccountModel eosAccountModel = EosAccountModel
                        .builder()
                        .name(accountName)
                        .created(Calendar.getInstance().getTime())
                        .build();
                if (!TextUtils.isEmpty(privateKey)) {
                    if (!TextUtils.isEmpty(password)) {
                        EosPrivateKey eosPrivateKey = new EosPrivateKey(privateKey);
                        EosPublicKey eosPublicKey = eosPrivateKey.getPublicKey();

                        String enc = mEncryptUtil.getEncryptString(privateKey, password);
                        String encPrivKey = mKeyStore.encryptString(enc, Constants.KEYSTORE_PRIV_KEY_ALIAS);
                        eosAccountModel.setPrivateKey(encPrivKey);
                        eosAccountModel.setPublicKey(eosPublicKey.toString());
                    } else {
                        throw new IllegalArgumentException();
                    }
                }

                mPocketAppManager.insert(eosAccountModel);

                return IMPORT_ACCOUNT_SUCCESS;
            } else {
                EosAccountModel eosAccountModel = accounts.get(0);

                if (TextUtils.isEmpty(privateKey) || !TextUtils.isEmpty(eosAccountModel.getPrivateKey())) {
                    return IMPORT_ACCOUNT_EXIST;
                } else {
                    // update private key
                    eosAccountModel.setPrivateKey(privateKey);
                    mPocketAppManager.update(eosAccountModel);

                    return IMPORT_ACCOUNT_SUCCESS;
                }
            }
        })
        .subscribeOn(mRxJavaSchedulers.getIo())
        .observeOn(mRxJavaSchedulers.getMainThread())
        .subscribe(result -> {
            if (result == IMPORT_ACCOUNT_SUCCESS) {
                mView.successImport();
            } else if (result == IMPORT_ACCOUNT_EXIST) {
                mView.existAccount();
            }
        }, e -> {
            if (e instanceof IllegalAccessException) {
                mView.privateKeyNotMatched();
            }

            e.printStackTrace();
        });
    }
}
