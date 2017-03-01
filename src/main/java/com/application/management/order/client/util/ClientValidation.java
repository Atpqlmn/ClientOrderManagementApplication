package com.application.management.order.client.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/**
 * Contains methods that validate client information
 */
public class ClientValidation {

    /*
    *  No validation for client first name, last name, street address and phone number
    *  */

    private static final Set<String> ISO_COUNTRIES = new HashSet<String>
            (Arrays.asList(Locale.getISOCountries()));

    /**
     * Validates whether a given string is a valid UUID
     * */
    public static boolean isUUIDValid(String strUuid) {
        try {
            UUID id = UUID.fromString(strUuid);
            if (id.toString().equals(strUuid)) {
                return true;
            }
        } catch (IllegalArgumentException | NullPointerException e) {
        }
        return false;
    }

    /**
     * Checks if country code is valid
     */
    public static boolean isCountryCodeValid(String countryCode) {
        if (countryCode == null) {
            return false;
        }
        return ISO_COUNTRIES.contains(countryCode);
    }
}
