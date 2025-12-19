package com.payments.api.adapters.input.rest.dto;

public record ConsumerRequestDto(
    String name,
    String document,
    String email
) {
}
