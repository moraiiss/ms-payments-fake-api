package com.payments.api.core.entities;

import com.payments.api.core.exceptions.InsufficientBalanceException;

import java.time.LocalDateTime;

public class Wallet {

    // TODO rever double x BigDecimal
    private double balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Wallet() {
        this.balance = 0;
        this.createdAt = LocalDateTime.now();
    }

    public static Wallet of() {
        return new Wallet();
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double value) {

        if (!this.hasSufficientBalance(value)) {
            throw new InsufficientBalanceException();
        }

        this.balance -= value;
        this.updatedAt = LocalDateTime.now();
    }

    public void credit(double value) {
        this.balance += value;
        this.updatedAt = LocalDateTime.now();
    }

    private boolean hasSufficientBalance(double value) {
        return !(value > this.getBalance());
    }
}
