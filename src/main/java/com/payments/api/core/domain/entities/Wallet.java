package com.payments.api.core.domain.entities;

import java.math.BigDecimal;

public record Wallet(
    Long id,
    BigDecimal balance
) {

    public static Wallet of() {
        return new Wallet(null, BigDecimal.ZERO);
    }
}
