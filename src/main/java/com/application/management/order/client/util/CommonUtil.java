package com.application.management.order.client.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    /**
     * Generates a barcode with a length specified by numberOfDigits
     * Generated barcode is not necessarily unique
     *
     * @param numberOfDigits specifies the length of a barcode
     * @return barcode with length of numberOfDigits. If numberOfDigits is less than 1, then
     * length 1 is returned
     */
    public static String generateBarcode(int numberOfDigits) {
        if (numberOfDigits < 1) {
            numberOfDigits = 1;
        }
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfDigits; i++) {
            sb.append(rd.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * Check if string is not null and its length is more than 0
     */
    public static boolean validateString(String str) {
        if (str != null && str.length() > 0) {
            return true;
        }
        return false;
    }

    /*
    * Controls that string consists of numeric characters and not null*/
    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        for (char ch : string.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    public static java.sql.Date fromStringToSqlDate(String date) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat(ProductAndOrderValidation.DATE_FORMAT);
        Date utilDate = format.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        return sqlDate;
    }
}
