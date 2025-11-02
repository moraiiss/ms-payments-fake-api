package com.payments.api.core.entities.contracts;

public interface Payer {
    void debit(double value);
}
