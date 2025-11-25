package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.request.CommonUserRequestDto;
import com.payments.api.controller.dto.response.CommonUserResponseDto;
import com.payments.api.core.domain.identity.CommonUser;
import com.payments.api.core.domain.identity.User;
import com.payments.api.core.domain.vo.CPF;
import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;
import com.payments.api.repository.jpa.entities.UserType;

public final class CommonUserRestMapper {

    private CommonUserRestMapper() { }

    public static CommonUser toDomain(CommonUserRequestDto commonUserRequestDto) {

        Email email = Email.of(commonUserRequestDto.email());
        Password password = Password.of(commonUserRequestDto.password());

        User user = User.of(email, password, UserType.COMMON);
        CPF document = CPF.of(commonUserRequestDto.document());

        return CommonUser.of(commonUserRequestDto.name(), document, user);
    }

    public static CommonUserResponseDto toDto(final CommonUser commonUser) {
        return new CommonUserResponseDto(commonUser.id(), commonUser.name(), commonUser.cpf(), commonUser.email(),
            commonUser.balance());
    }

}
