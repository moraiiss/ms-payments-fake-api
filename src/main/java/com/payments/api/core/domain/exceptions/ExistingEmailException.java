package com.payments.api.core.domain.exceptions;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException() {
        super("The email address provided already exists!");
    }
}
