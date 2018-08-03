package app.eospocket.android.utils;

import android.support.annotation.NonNull;

// https://www.javacodeexamples.com/check-password-strength-in-java-example/668
public class PasswordChecker {

    public static final int WEAK = 0;
    public static final int MEDIUM = 1;
    public static final int STRONG = 2;
    public static final int VERY_STRONG = 3;

    public static int calculatePasswordStrength(@NonNull String password) {

        //total score of password
        int iPasswordScore = 0;

        if (password.length() < 8) {
            return 0;
        } else if(password.length() >= 10) {
            iPasswordScore += 2;
        } else {
            iPasswordScore += 1;
        }

        //if it contains one digit, add 2 to total score
        if (password.matches("(?=.*[0-9]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one lower case letter, add 2 to total score
        if (password.matches("(?=.*[a-z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one upper case letter, add 2 to total score
        if (password.matches("(?=.*[A-Z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one special character, add 2 to total score
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) {
            iPasswordScore += 2;
        }

        if (iPasswordScore <= 4) {
            return WEAK;
        } else if (iPasswordScore <= 6) {
            return MEDIUM;
        } else if (iPasswordScore <= 8) {
            return STRONG;
        } else {
            return VERY_STRONG;
        }
    }
}
