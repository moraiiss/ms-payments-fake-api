package com.payments.api.core.entities.payments;

import java.math.BigDecimal;

public interface Payer {

    void debit(BigDecimal value);

}
