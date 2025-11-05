package com.payments.api.controller.dto;

public record SellerRequestDto(
    String socialReason,
    String fantasyName,
    String document,
    String email,
    String password
) {
}
