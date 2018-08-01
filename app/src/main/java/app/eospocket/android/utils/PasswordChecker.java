package app.eospocket.android.utils;

import android.support.annotation.NonNull;

// https://www.javacodeexamples.com/check-password-strength-in-java-example/668
public class PasswordChecker {

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

        return iPasswordScore;

    }
}
