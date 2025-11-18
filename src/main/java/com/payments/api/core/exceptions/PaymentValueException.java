package com.payments.api.core.exceptions;

public class PaymentValueException extends RuntimeException {
    public PaymentValueException() {
        super("Payment value can't be zero");
    }
}
