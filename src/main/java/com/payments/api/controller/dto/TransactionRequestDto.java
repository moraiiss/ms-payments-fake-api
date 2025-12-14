package com.payments.api.controller.dto;

import java.math.BigDecimal;

public record TransactionRequestDto(
    Long payer,
    Long payee,
    BigDecimal amount
) {
}
