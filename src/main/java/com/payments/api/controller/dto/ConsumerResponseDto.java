package com.payments.api.controller.dto;

public record ConsumerResponseDto(
    String name,
    String document,
    String email
) {
}
