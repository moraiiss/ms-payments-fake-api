package com.payments.api.core.entities.payments;

import java.math.BigDecimal;

public interface Payee {

    void credit(BigDecimal value);

}
