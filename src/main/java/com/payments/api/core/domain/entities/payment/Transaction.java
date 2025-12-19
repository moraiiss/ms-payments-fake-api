package com.payments.api.core.domain.entities.payment;

import com.payments.api.core.domain.exceptions.TransactionException;

import java.math.BigDecimal;

public record Transaction(
    Long payer,
    Long payee,
    BigDecimal amount
) {
    public Transaction {

//        if (payer.equals(payee)) {
//            throw new TransactionException("Payer and payee can't be equals!");
//        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("Transaction amount must be greater zero");
        }

    }

    public static Transaction of(final Long payer, final Long payee, final BigDecimal amount) {
        return new Transaction(payer, payee, amount);
    }
}
