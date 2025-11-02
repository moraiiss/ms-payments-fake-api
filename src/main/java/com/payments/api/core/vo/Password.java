package com.payments.api.core.vo;

import static java.util.Objects.requireNonNull;

public class Password {
    private String key;

    private Password(final String key) {
        requireNonNull(key, "Key can't be null");

        if (!isValidKey(key)) {
            throw new IllegalArgumentException("The password does not meet the minimum security requirements");
        }

        this.key = key;
    }

    public static Password of(final String key) {
        return new Password(key);
    }

    private boolean isValidKey(final String key) {

        boolean hasLength = key.length() > 8;
        boolean hasSpecialChar = key.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        boolean hasUpperCase = key.matches(".*[A-Z].*");
        boolean hasDigit = key.matches(".*\\d.*");

        return hasLength && hasSpecialChar && hasUpperCase && hasDigit;
    }

    public String getKey() {
        return key;
    }
}
