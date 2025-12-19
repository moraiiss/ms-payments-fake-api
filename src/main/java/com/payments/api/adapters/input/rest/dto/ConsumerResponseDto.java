package com.payments.api.adapters.input.rest.dto;

public record ConsumerResponseDto(
    Long id,
    String name,
    String document,
    String email
) {
}
