package com.payments.api.adapters.input.rest.dto;

public record SellerRequestDto(
    String socialReason,
    String fantasyName,
    String document,
    String email
) {
}
