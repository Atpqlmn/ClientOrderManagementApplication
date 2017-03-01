package com.application.management.order.client.util;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductAndOrderValidationTest {

    @Test
    public void testInvalidBarcode() throws Exception {
        String fewDigits = "91919183";
        String charactersBarcode = "23485t43f";
        String tooManyDigits = "1647583818123";

        assertFalse("To few digits", ProductAndOrderValidation.isBarcodeValid(""));
        assertFalse("Characters in a barcode", ProductAndOrderValidation.isBarcodeValid(charactersBarcode));
        assertFalse("Too many digits", ProductAndOrderValidation.isBarcodeValid(tooManyDigits));
    }

    @Test
    public void testValidBarcode() throws Exception {
        String barcode1 = "0000000000";
        String barcode2 = "5487645955";
        assertTrue(ProductAndOrderValidation.isBarcodeValid(barcode1));
        assertTrue(ProductAndOrderValidation.isBarcodeValid(barcode2));
    }

    @Test
    public void testInvalidPrice() throws Exception {
        BigDecimal validPrice1 = new BigDecimal("0.01");
        BigDecimal validPrice2 = new BigDecimal("6");
        BigDecimal validPrice3 = new BigDecimal("5673.12");

        assertTrue(ProductAndOrderValidation.isPriceValid(validPrice1));
        assertTrue(ProductAndOrderValidation.isPriceValid(validPrice2));
        assertTrue(ProductAndOrderValidation.isPriceValid(validPrice3));
    }

    @Test
    public void testValidPrice() throws Exception {
        BigDecimal invalidPrice1 = new BigDecimal("0.0");
        BigDecimal invalidPrice2 = new BigDecimal("-1.25");
        BigDecimal invalidPrice3 = new BigDecimal("0.001");

        assertFalse("Price is 0", ProductAndOrderValidation.isPriceValid(invalidPrice1));
        assertFalse("Negative price", ProductAndOrderValidation.isPriceValid(invalidPrice2));
        assertFalse("Too many digits after the decimal point ",
                ProductAndOrderValidation.isPriceValid(invalidPrice3));
    }

    @Test
    public void testInvalidDate() throws Exception {
        String minDate = "1900-01-01";
        String invalidFormat = "1999/01/01";
        String monthOufOfRange = "1900-30-01";
        String invalidDayFebruary = "1999-02-31";
        String randomCharacters = "djdfkg";
        String futureDate = "2100-05-30";
        String dayOutOfRange = "2010-01-32";

        assertFalse("Invalid, as all dates should be strictly after " + minDate,
                ProductAndOrderValidation.isDateValid(minDate));
        assertFalse("Invalid format", ProductAndOrderValidation.isDateValid(invalidFormat));
        assertFalse("Invalid month", ProductAndOrderValidation.isDateValid(monthOufOfRange));
        assertFalse("Invalid day for February", ProductAndOrderValidation.isDateValid(invalidDayFebruary));
        assertFalse("Not a date", ProductAndOrderValidation.isDateValid(randomCharacters));
        assertFalse("Future date, not valid", ProductAndOrderValidation.isDateValid(futureDate));
        assertFalse("Day out of range", ProductAndOrderValidation.isDateValid(dayOutOfRange));

    }

    @Test
    public void testValidDate() throws Exception {
        String validDate1 = "1999-01-31";
        String validDate2 = "2010-05-30";
        String validDate3 = "2017-01-30";

        assertTrue(ProductAndOrderValidation.isDateValid(validDate1));
        assertTrue(ProductAndOrderValidation.isDateValid(validDate2));
        assertTrue(ProductAndOrderValidation.isDateValid(validDate3));
    }
}