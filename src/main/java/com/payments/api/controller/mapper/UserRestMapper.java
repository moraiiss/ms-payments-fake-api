package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.UserRequestDto;
import com.payments.api.controller.dto.UserResponseDto;
import com.payments.api.core.domain.entities.User;
import com.payments.api.core.domain.entities.Wallet;
import com.payments.api.core.domain.vo.Password;

import java.math.BigDecimal;

public class UserRestMapper {

    private UserRestMapper() { }

    public static UserResponseDto toDto(final User user) {
        return new UserResponseDto(
            user.id(),
            user.name(),
            user.document(),
            user.email(),
            user.userType()
        );
    }

    public static User toDomain(final UserRequestDto requestDto) {
        return new User(
            null,
            requestDto.name(),
            requestDto.document(),
            requestDto.email(),
            // todo gerar senha aleatória
            Password.of("q1@We34rt5"),
            requestDto.userType(),
            // todo static factory
            new Wallet(null, BigDecimal.ZERO)
        );
    }

}
