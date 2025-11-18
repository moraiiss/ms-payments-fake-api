package com.payments.api.core.entities.payments;

import com.payments.api.core.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;

public class Wallet {

    private final Long id;

    private BigDecimal balance;

    private Wallet(final Long id, final BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public static Wallet of() {
        return new Wallet(null, BigDecimal.ZERO);
    }

    public static Wallet of(final Long id, final BigDecimal balance) {
        return new Wallet(id, balance);
    }

    public void debit(BigDecimal value) {

        if (this.hasSufficientBalance(value)) {
            throw new InsufficientBalanceException();
        }

        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    private boolean hasSufficientBalance(BigDecimal value) {
        return this.balance.compareTo(value) > 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
