package com.payments.api.core.domain.entities;

import java.math.BigDecimal;

public record Wallet(
    Long id,
    BigDecimal balance
) {
}
