package com.payments.api.controller.dto;

import java.math.BigDecimal;

public record PaymentResponseDto(
    Long payer,
    Long payee,
    BigDecimal amount
) {
}
