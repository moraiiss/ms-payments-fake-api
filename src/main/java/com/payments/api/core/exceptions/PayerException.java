package com.payments.api.core.exceptions;

public class PayerException extends RuntimeException {
    public PayerException() {
        super("Payer and payee can't be equals");
    }
}
