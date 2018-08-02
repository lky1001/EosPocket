package app.eospocket.android.utils;

public interface Encryption {

    String encrypt(String data, String salt);
    String decrypt(String data, String salt);
}
