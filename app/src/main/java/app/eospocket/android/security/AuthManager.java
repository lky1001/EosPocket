package app.eospocket.android.security;

import android.os.Build;
import android.security.keystore.UserNotAuthenticatedException;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.security.keystore.KeyStore;
import app.eospocket.android.utils.EncryptUtil;

@Singleton
public class AuthManager {

    static final String ALIAS_PINCODE = "alias_pincode";

    private CustomPreference mCustomPreference;

    private KeyStore mKeyStore;

    private List<String> mAliasList = Arrays.asList(ALIAS_PINCODE);

    @Inject
    public AuthManager(CustomPreference customPreference, KeyStore keyStore) {
        this.mCustomPreference = customPreference;
        this.mKeyStore = keyStore;
    }

    public void init() {
        for (String alias : mAliasList) {
            if (!mKeyStore.createKeys(alias)) {
                // todo - not support algorithm error
            }
        }
    }

    public void setUsePinCode(boolean use) {
        mCustomPreference.setUsePinCode(use);
    }

    public boolean getUsePinCode() {
        return mCustomPreference.getUsePinCode();
    }

    public void setPinCode(String pinCode) {
        String enc = mKeyStore.encryptString(pinCode, ALIAS_PINCODE);
        mCustomPreference.setPinCode(enc);
    }

    public String getPinCode() {
        String enc = mCustomPreference.getPinCode();

        return mKeyStore.decryptString(enc, ALIAS_PINCODE);
    }

    public void walletInitialized() {
        mCustomPreference.setKeyStoreVersion(Build.VERSION.SDK_INT);
        mCustomPreference.setInitWallet(true);
    }

    public boolean isWalletInitialized() {
        return mCustomPreference.getInitWallet();
    }
}
