package com.alk.service.validator;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.regex.Pattern;

public final class ValidationUtils {

    private ValidationUtils() {}

    public static void requireString(String value, String fieldName) {
        if (!StringUtils.hasText(value)) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
    }

    public static void requireNotNull(Object o, String fieldName) {
        if (o == null) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
    }

    public static boolean isDigits(String s) {
        if (!StringUtils.hasText(s)) return false;
        return s.chars().allMatch(Character::isDigit);
    }

    private static final Pattern ALPHANUMERIC = Pattern.compile("^[A-Za-z0-9]+$");
    public static boolean isAlphanumeric(String s) {
        if (!StringUtils.hasText(s)) return false;
        return ALPHANUMERIC.matcher(s).matches();
    }

    private static final Pattern EMAIL_SIMPLE = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    public static boolean isValidEmail(String email) {
        if (!StringUtils.hasText(email)) return false;
        return EMAIL_SIMPLE.matcher(email).matches();
    }

    public static void requireValidEmailIfPresent(String email, String fieldName) {
        if (StringUtils.hasText(email) && !isValidEmail(email)) {
            throw new IllegalArgumentException(fieldName + " is not a valid email");
        }
    }

    public static void requireDob(String dob, String fieldName) {
        if (dob == null) throw new IllegalArgumentException(fieldName + " is required");
    }

    public static void requireAadhaarLinkageKeyPresent(String maybeKey) {
        if (!StringUtils.hasText(maybeKey)) {
            throw new IllegalArgumentException("aadhaarLinkageKey (oldAadhaarLinkageKey) is required for this action");
        }
    }
}
