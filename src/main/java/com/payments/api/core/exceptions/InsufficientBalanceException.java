package com.payments.api.core.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Wallet does not have sufficient balance");
    }
}
