package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.request.MerchantUserRequestDto;
import com.payments.api.controller.dto.response.MerchantUserResponseDto;
import com.payments.api.core.domain.identity.MerchantUser;
import com.payments.api.core.domain.identity.User;
import com.payments.api.core.domain.vo.CNPJ;
import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;
import com.payments.api.core.domain.identity.UserType;

public final class MerchantUserRestMapper {

    private MerchantUserRestMapper() { }

    public static MerchantUser toDomain(final MerchantUserRequestDto merchantUserRequestDto) {

        Email email = Email.of(merchantUserRequestDto.email());
        Password password = Password.of(merchantUserRequestDto.password());

        User user = User.of(email, password, UserType.MERCHANT);
        CNPJ document = CNPJ.of(merchantUserRequestDto.document());

        return MerchantUser.of(user, document, merchantUserRequestDto.fantasyName(),
            merchantUserRequestDto.socialReason());
    }

    public static MerchantUserResponseDto toDto(final MerchantUser merchantUser) {
        return new MerchantUserResponseDto(merchantUser.id(), merchantUser.fantasyName(), merchantUser.socialReason(),
            merchantUser.getDocumentNumber(), merchantUser.email(), merchantUser.balance());
    }



}
