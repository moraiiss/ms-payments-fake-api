package com.payments.api.controller.dto;

public record ConsumerRequestDto(
    String name,
    String document,
    String email
) {
}
