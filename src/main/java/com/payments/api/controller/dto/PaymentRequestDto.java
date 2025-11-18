package com.payments.api.controller.dto;

import java.math.BigDecimal;

public record PaymentRequestDto(
    Long payer,
    Long payee,
    BigDecimal amount
) {
}
