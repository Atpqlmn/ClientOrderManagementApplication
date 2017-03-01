package com.application.management.order.client.util;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class ClientValidationTest {

    @Test
    public void testInvalidCountryCode() throws Exception {
        String country = "Nepal";
        String digits = "76";
        String invalidCode = "QQ";
        assertFalse(ClientValidation.isCountryCodeValid(invalidCode));
        assertFalse(ClientValidation.isCountryCodeValid(country));
        assertFalse(ClientValidation.isCountryCodeValid(digits));
    }

    @Test
    public void testValidCountryCode() throws Exception {
        String codeEstonia = "EE";
        String codeJapan = "JP";
        assertTrue(ClientValidation.isCountryCodeValid(codeEstonia));
        assertTrue(ClientValidation.isCountryCodeValid(codeJapan));
    }

    @Test
    public void testValidUUID() throws Exception {
        // valid uuid
        String uuid = "067e6162-3b6f-4ae2-a171-2470b63dff00";
        String uuid2 = "09a949b6-f775-44e8-a991-bb62dc143b75";
        assertTrue("Valid UUID", ClientValidation.isUUIDValid(uuid));
    }

    @Test
    public void testInvalidUUID() throws Exception{

        String uuidMissingCharacters = "067e612-3b6f-4ae2-a11-247b63dff00";
        String uuidMoreCharacters = "067e612-3b6fa-4ae2-a11a-247b63dff00aaa";
        String invalidUuidCharacters = "jfohfsifksnfkuie";
        String invalidUuidDigits = "2390437594357";
        String invalidNull = null;


        assertFalse("Null passed to the function", ClientValidation.isUUIDValid(invalidNull));
        assertFalse("Digits passed to the function" ,ClientValidation.isUUIDValid(invalidUuidDigits));
        assertFalse("Characters passed to the function", ClientValidation.isUUIDValid(invalidUuidCharacters));
        assertFalse("Missing characters in UUID" , ClientValidation.isUUIDValid(uuidMissingCharacters));
        assertFalse("More characters in UUID", ClientValidation.isUUIDValid(uuidMoreCharacters));
    }

}