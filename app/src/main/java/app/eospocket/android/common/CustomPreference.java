package app.eospocket.android.common;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EosSettings {

        public String nodeosHost;
        public int nodeosPort;
        public String eosWallet;
    }
}
