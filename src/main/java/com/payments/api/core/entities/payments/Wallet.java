package com.payments.api.core.entities.payments;

import com.payments.api.core.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wallet {

    private BigDecimal balance;

    private final LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Wallet() {
        this.balance = BigDecimal.ZERO;
        this.createdAt = LocalDateTime.now();
    }

    public static Wallet of() {
        return new Wallet();
    }

    public void debit(BigDecimal value) {

        if (!this.hasSufficientBalance(value)) {
            throw new InsufficientBalanceException();
        }

        this.balance = this.balance.subtract(value);
        this.updatedAt = LocalDateTime.now();
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
        this.updatedAt = LocalDateTime.now();
    }

    private boolean hasSufficientBalance(BigDecimal value) {
        return !(this.balance.compareTo(value) < 0);
    }
}
