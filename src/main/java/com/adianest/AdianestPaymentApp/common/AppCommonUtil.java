package com.adianest.AdianestPaymentApp.common;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class AppCommonUtil {

    public static String firstCharacterToUpperCase(String string) {
        if (string == null || string.isEmpty()) return "-";
        String[] split = string.split(" ");

        String updateString = "";
        int i = 0;
        for (String s : split) {
            String f = s.substring(0, 1).toUpperCase();
            String l = s.substring(1).toLowerCase();

            updateString += (i > 0) ? (f + l) + " " : (f + l) ;
            i++;
        }
        return updateString;
    }

    public static String toRupiahFormat (String value) {
        if (value == null) {
            throw new NullPointerException("Value is null");
        }
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("###,###,###.##");
        df.setDecimalFormatSymbols(otherSymbols);
        //df.setDecimalSeparatorAlwaysShown(true);
        //df.setMinimumFractionDigits(2);

        return df.format(Double.parseDouble(value));
    }
}
