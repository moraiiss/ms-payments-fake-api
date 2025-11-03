package com.payments.api.core.entities.payments;

public interface Payer {

    void debit(double value);

}
