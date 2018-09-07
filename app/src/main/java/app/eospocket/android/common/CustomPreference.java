package app.eospocket.android.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Singleton
public class CustomPreference {

    private static final String CUSTOM_PREFERENCE = "custom_preference";
    private static final String PREFERENCE_SETTINGS = "preference_settings";

    private final SharedPreferences mSharedPreferences;

    private EosSetting mSettings;

    @Inject
    public CustomPreference(@NonNull Context context) {
        mSharedPreferences = context.getSharedPreferences(CUSTOM_PREFERENCE, Activity.MODE_PRIVATE);
    }

    public void loadSettings() {
        ObjectMapper mapper = new ObjectMapper();

        String data = mSharedPreferences.getString(PREFERENCE_SETTINGS, null);

        if (!TextUtils.isEmpty(data)) {
            try {
                mSettings = mapper.readValue(data, EosSetting.class);
            } catch (IOException e) {
                mSettings = new EosSetting();
                mSettings.setUsePinCode(true);
                saveSettings();
            }
        } else {
            mSettings = new EosSetting();
            mSettings.setUsePinCode(true);
            saveSettings();
        }
    }

    private void saveSettings() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String data = mapper.writeValueAsString(mSettings);

            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(PREFERENCE_SETTINGS, data);
            editor.apply();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void storeEncryptedIv(byte[] data, String name) {
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(name, base64);
        edit.apply();
    }

    public byte[] retrieveEncryptedIv(String name) {
        String base64 = mSharedPreferences.getString(name, null);
        if (base64 == null) return null;
        return Base64.decode(base64, Base64.DEFAULT);
    }

    public void destroyEncryptedIv(String name) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.remove(name);
        edit.apply();
    }

    public void setInitWallet(boolean isInit) {
        this.mSettings.setInitWallet(isInit);
        saveSettings();
    }

    public boolean getInitWallet() {
        return mSettings.isInitWallet();
    }

    public void setPinCode(String pinCode) {
        this.mSettings.setPinCode(pinCode);
        saveSettings();
    }

    public String getPinCode() {
        return this.mSettings.getPinCode();
    }

    public void setKeyStoreVersion(int keyStoreVersion) {
        mSettings.setKeyStoreVersion(keyStoreVersion);
        saveSettings();
    }

    public int getKeyStoreVersion() {
        return mSettings.getKeyStoreVersion();
    }

    public void setUsePinCode(boolean usePinCode) {
        mSettings.setUsePinCode(usePinCode);
        saveSettings();
    }

    public boolean getUsePinCode() {
        return mSettings.isUsePinCode();
    }

    public void setParseActionSeq(long actionSeq) {
        mSettings.setParseActionSeq(actionSeq);
        saveSettings();
    }

    public long getParseActionSeq() {
        return mSettings.getParseActionSeq();
    }

    public void changeSelectedEosAccountId(int id) {
        mSettings.setSelectedEosAccountId(id);
        saveSettings();
    }

    public int getSelectedEosAccountId() {
        return mSettings.getSelectedEosAccountId();
    }
}
