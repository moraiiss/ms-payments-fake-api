package com.payments.api.core.domain.exceptions;

public class PasswordSecurityException extends RuntimeException {
    public PasswordSecurityException() {
        super("The password does not meet the minimum security requirements");
    }
}
