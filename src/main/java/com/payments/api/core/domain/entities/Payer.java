package com.payments.api.core.domain.entities;

import java.math.BigDecimal;

public interface Payer {

    Wallet debit(BigDecimal amount);

}
