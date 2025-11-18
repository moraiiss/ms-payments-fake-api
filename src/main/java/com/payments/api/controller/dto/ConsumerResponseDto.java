package com.payments.api.controller.dto;

import java.math.BigDecimal;

public record ConsumerResponseDto(
    Long id,
    String name,
    String document,
    String email,
    BigDecimal balance
) {
}
