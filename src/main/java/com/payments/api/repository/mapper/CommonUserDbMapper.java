package com.payments.api.repository.mapper;

import com.payments.api.core.domain.identity.CommonUser;
import com.payments.api.core.domain.vo.CPF;
import com.payments.api.repository.jpa.entities.CommonUserEntity;

public final class CommonUserDbMapper {

    private CommonUserDbMapper() { }

    public static CommonUser toDomain(final CommonUserEntity commonUserEntity) {

        return new CommonUser(
            commonUserEntity.getId(),
            UserDbMapper.toDomain(commonUserEntity.getUser()),
            CPF.of(commonUserEntity.getDocument()),
            commonUserEntity.getName()
        );
    }

    public static CommonUserEntity toEntity(final CommonUser commonUser) {
        return new CommonUserEntity(
            null,
            commonUser.name(),
            commonUser.cpf(),
            UserDbMapper.toEntity(commonUser.user())
        );
    }

}
