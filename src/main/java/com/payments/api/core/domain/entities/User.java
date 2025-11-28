package com.payments.api.core.domain.entities;

import com.payments.api.core.domain.vo.Password;

public record User(
    Long id,
    String name,
    String document,
    String email,
    Password password,
    UserType userType,
    Wallet wallet
) {
}
