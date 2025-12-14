package com.payments.api.core.domain.entities;

import java.math.BigDecimal;

public interface Payee {

    Wallet credit(BigDecimal amount);

}
