package com.payments.api.controller.dto;

import com.payments.api.core.domain.entities.UserType;

public record UserRequestDto(
    String name,
    String document,
    String email,
    UserType userType
) {
}
