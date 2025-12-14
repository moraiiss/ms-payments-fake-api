package com.payments.api.controller.dto;

public record SellerResponseDto(
    Long id,
    String socialReason,
    String fantasyName,
    String document,
    String email
) {
}
