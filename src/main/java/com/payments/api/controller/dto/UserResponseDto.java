package com.payments.api.controller.dto;

import com.payments.api.core.domain.entities.UserType;

public record UserResponseDto(
    Long id,
    String name,
    String document,
    String email,
    UserType userType
) {
}
