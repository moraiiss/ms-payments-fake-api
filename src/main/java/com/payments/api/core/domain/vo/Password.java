package com.payments.api.core.domain.vo;

import com.payments.api.core.domain.exceptions.PasswordSecurityException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static java.util.Objects.requireNonNull;

public record Password(
    String key
) {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private static final int KEY_SIZE = 8;

    public Password {
        requireNonNull(key, "Password key can't be null");

        if (!isValidKey(key)) {
            throw new PasswordSecurityException();
        }

        key = ENCODER.encode(key);
    }

    public static Password of(final String key) {
        return new Password(key);
    }

    private boolean isValidKey(final String key) {

        boolean hasLength = key.length() > KEY_SIZE;
        boolean hasSpecialChar = key.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        boolean hasUpperCase = key.matches(".*[A-Z].*");
        boolean hasDigit = key.matches(".*\\d.*");

        return hasLength && hasSpecialChar && hasUpperCase && hasDigit;
    }
}
