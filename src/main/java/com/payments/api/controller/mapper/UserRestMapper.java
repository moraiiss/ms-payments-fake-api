package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.UserRequestDto;
import com.payments.api.controller.dto.UserResponseDto;
import com.payments.api.core.domain.entities.User;
import com.payments.api.core.domain.entities.UserType;
import com.payments.api.core.domain.entities.Wallet;
import com.payments.api.core.domain.vo.*;

public class UserRestMapper {

    private UserRestMapper() { }

    public static UserResponseDto toDto(final User user) {
        return new UserResponseDto(
            user.id(),
            user.name(),
            user.getDocumentNumber(),
            user.getEmailAddress(),
            user.userType()
        );
    }

    public static User toDomain(final UserRequestDto requestDto) {
        Document document = requestDto.userType() == UserType.COMMON
            ? CPF.of(requestDto.document())
            : CNPJ.of(requestDto.document());

        return new User(
            null,
            requestDto.name(),
            document,
            Email.of(requestDto.email()),
            Password.of(),
            requestDto.userType(),
            Wallet.of()
        );
    }

}
