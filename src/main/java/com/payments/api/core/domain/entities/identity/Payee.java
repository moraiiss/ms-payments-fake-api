package com.payments.api.core.domain.entities.identity;

import com.payments.api.core.domain.entities.payment.Wallet;

import java.math.BigDecimal;

public interface Payee {

    Wallet credit(BigDecimal amount);

}
