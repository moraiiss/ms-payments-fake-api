package com.payments.api.adapters.input.rest.dto;

import java.math.BigDecimal;

public record TransactionRequestDto(
    Long payer,
    Long payee,
    BigDecimal amount
) {
}
