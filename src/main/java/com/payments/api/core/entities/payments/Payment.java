package com.payments.api.core.entities.payments;

import com.payments.api.core.exceptions.PaymentValueException;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public class Payment {

    private final Long payer;

    private final Long payee;

    private final BigDecimal amount;

    private Payment(final Long payer, final Long payee, final BigDecimal amount) {
        requireNonNull(payer, "Payer can't be empty");
        requireNonNull(payee, "Payee can't be empty");

        if (amount.equals(BigDecimal.ZERO)) {
            throw new PaymentValueException();
        }

        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }

    public static Payment of(final Long payer, final Long payee, final BigDecimal amount) {
        return new Payment(payer, payee, amount);
    }

    public Long getPayer() {
        return payer;
    }

    public Long getPayee() {
        return payee;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
