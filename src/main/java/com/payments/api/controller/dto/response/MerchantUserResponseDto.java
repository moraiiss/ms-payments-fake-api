package com.payments.api.controller.dto.response;

import java.math.BigDecimal;

public record MerchantUserResponseDto(
    Long id,
    String fantasyName,
    String socialReason,
    String document,
    String email,
    BigDecimal balance
) {
}
