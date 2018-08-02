package app.eospocket.android.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private EosSettings mSettings;

    @Inject
    public CustomPreference(@NonNull Context context) {
        mSharedPreferences = context.getSharedPreferences(CUSTOM_PREFERENCE, Activity.MODE_PRIVATE);
    }

    public void loadSettings() {
        ObjectMapper mapper = new ObjectMapper();

        String data = mSharedPreferences.getString(PREFERENCE_SETTINGS, null);

        if (!TextUtils.isEmpty(data)) {
            try {
                mSettings = mapper.readValue(data, EosSettings.class);
            } catch (IOException e) {
                mSettings = new EosSettings();
            }
        } else {
            mSettings = new EosSettings();
        }
    }

    public void saveSettings() {
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

    public void saveEosWallet(String wallet) {
        mSettings.eosWallet = wallet;
        saveSettings();
    }

    public String looadEosWallet() {
        return mSettings.eosWallet;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EosSettings {

        String nodeosHost;
        int nodeosPort;
        String eosWallet;
    }
}
