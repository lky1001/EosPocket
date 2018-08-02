package app.eospocket.android.utils;

import android.support.annotation.NonNull;

import javax.annotation.Nullable;

public class EncryptionImpl implements Encryption {

    private static final String KEY = EncryptionImpl.class.getPackage().toString();

    @Nullable
    @Override
    public String encrypt(@NonNull String data, @NonNull String salt) {
        byte[] iv = new byte[16];
        se.simbio.encryption.Encryption encryption = se.simbio.encryption.Encryption.getDefault(KEY, salt, iv);

        return encryption.encryptOrNull(data);
    }

    @Nullable
    @Override
    public String decrypt(@NonNull String data, @NonNull String salt) {
        byte[] iv = new byte[16];
        se.simbio.encryption.Encryption encryption = se.simbio.encryption.Encryption.getDefault(KEY, salt, iv);

        return encryption.decryptOrNull(data);
    }
}
