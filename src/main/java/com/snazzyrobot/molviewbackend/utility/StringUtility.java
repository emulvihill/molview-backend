package com.snazzyrobot.molviewbackend.utility;

public class StringUtility {
    public static void checkEmptyField(String field, String fieldName) {
        if (field == null || field.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }
}
