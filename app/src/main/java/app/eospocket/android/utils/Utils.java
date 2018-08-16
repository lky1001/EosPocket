package app.eospocket.android.utils;

import java.text.DecimalFormat;

public class Utils {

    private static final DecimalFormat BALANCE_FORMAT = new DecimalFormat("#,##0.000000");
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,##0");
    private static final DecimalFormat USD_FORMAT = new DecimalFormat("#,##0.000");
    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#,##0.00");

    public static String formatBalance(float balance) {
        return BALANCE_FORMAT.format(balance);
    }
}
