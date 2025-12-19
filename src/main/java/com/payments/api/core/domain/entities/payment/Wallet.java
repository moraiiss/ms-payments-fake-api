package com.payments.api.core.domain.entities.payment;

import com.payments.api.core.domain.exceptions.TransactionException;

import java.math.BigDecimal;

public record Wallet(
    Long id,
    BigDecimal balance
) {

    public static Wallet of() {
        return new Wallet(null, BigDecimal.ZERO);
    }

    public Wallet debit(final BigDecimal amount) {

        if (amount.compareTo(balance) > 0) {
            throw new TransactionException("Wallet with insufficient funds for transfer!");
        }

        return new Wallet(id, balance.subtract(amount));
    }

    public Wallet credit(final BigDecimal amount) {
        return new Wallet(id, balance.add(amount));
    }
}
