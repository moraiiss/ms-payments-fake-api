package com.payments.api.controller.dto.response;

import java.math.BigDecimal;

public record CommonUserResponseDto(
    Long id,
    String name,
    String document,
    String email,
    BigDecimal balance
) {
}
