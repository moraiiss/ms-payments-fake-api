package com.payments.api.controller.dto.request;

public record CommonUserRequestDto(
    String name,
    String document,
    String email,
    String password
) {
}
