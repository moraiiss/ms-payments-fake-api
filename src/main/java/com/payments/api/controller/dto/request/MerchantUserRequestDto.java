package com.payments.api.controller.dto.request;

public record MerchantUserRequestDto(
    String fantasyName,
    String socialReason,
    String document,
    String email,
    String password
) {
}
