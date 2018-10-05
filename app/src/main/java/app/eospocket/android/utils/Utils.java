package app.eospocket.android.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import java.text.DecimalFormat;

import app.eospocket.android.common.Constants;

public class Utils {

    private static final DecimalFormat BALANCE_FORMAT = new DecimalFormat("#,##0.0000");
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,##0");
    private static final DecimalFormat USD_FORMAT = new DecimalFormat("#,##0.000");
    private static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#,##0.00");

    public static String formatBalance(double balance) {
        return BALANCE_FORMAT.format(balance);
    }

    public static String formatBalanceWithEOSSymbol(double balance) {
        return formatBalanceWithSymbol(balance, Constants.EOS_SYMBOL);
    }

    public static String formatBalanceWithSymbol(double balance, String symbol) {
        return BALANCE_FORMAT.format(balance) + " " + symbol;
    }

    public static String formatUsd(double balance) {
        return USD_FORMAT.format(balance);
    }

    public static void copyToClipboard(Context context, String content) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", content);
        clipboard.setPrimaryClip(clip);
    }
}
