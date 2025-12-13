package com.payments.api.core.domain.entities;

import java.math.BigDecimal;

public record Transaction(
    Long payer,
    Long payee,
    BigDecimal amount
) {
}
