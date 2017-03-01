package com.application.management.order.client.util;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class will be used for validation of product fields and order fields
 */
public class ProductAndOrderValidation {

    private static final int BARCODE_LENGTH = 10;
    private static final String PRICE_REG_EXPRESSION = "[0-9]+(.[0-9]{1,2})?";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String MINIMUM_DATE = "1900-01-01";

    /**
     * Checks if barcode is a valid string consisting of 10 digits
     * Uniqueness is not checked
     */
    public static boolean isBarcodeValid(String barcode) {
        if (CommonUtil.isNumeric(barcode) && barcode.length() == BARCODE_LENGTH) {
            return true;
        }
        return false;
    }

    /**
     * The price format should be [0-9]+(.[0-9]{1,2})?
     * Price cannot be 0
     */
    public static boolean isPriceValid(BigDecimal price) {
        if (price == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(PRICE_REG_EXPRESSION);
        Matcher matcher = pattern.matcher(price.toPlainString());
        if (matcher.matches() && price.compareTo(new BigDecimal(0)) == 1) {
            return true;
        }
        return false;
    }

    /**
     * Tests if a string is a valid date with a format of yyyy-MM-dd.
     * If MINIMUM_DATE is passed, the function will return false due to after returning
     * true only for strictly later dates
     * Does not allow to insert future dates
     */
    public static boolean isDateValid(String strDate) {
        if (strDate == null) {
            return false;
        }
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        // does not allow string in the form "1900-30-01"
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(strDate);
            if (date.after(sdf.parse(MINIMUM_DATE)) && !date.after(currentDate)) {
                return true;
            }

        } catch (ParseException e) {
        }
        return false;
    }

}
