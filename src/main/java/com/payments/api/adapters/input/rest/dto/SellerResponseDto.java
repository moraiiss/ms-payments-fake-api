package com.payments.api.adapters.input.rest.dto;

public record SellerResponseDto(
    Long id,
    String socialReason,
    String fantasyName,
    String document,
    String email
) {
}
