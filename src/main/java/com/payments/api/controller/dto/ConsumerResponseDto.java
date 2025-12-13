package com.payments.api.controller.dto;

public record ConsumerResponseDto(
    Long id,
    String name,
    String document,
    String email
) {
}
