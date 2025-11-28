package com.payments.api.repository.mapper;

import com.payments.api.core.domain.entities.User;
import com.payments.api.core.domain.entities.UserType;
import com.payments.api.core.domain.vo.*;
import com.payments.api.repository.jpa.entities.UserEntity;

public final class UserDbMapper {

    private UserDbMapper() { }

    public static User toDomain(final UserEntity userEntity) {
        Document document = userEntity.getUserType() == UserType.COMMON
            ? CPF.of(userEntity.getDocument())
            : CNPJ.of(userEntity.getDocument());

        return new User(
            userEntity.getId(),
            userEntity.getName(),
            document,
            Email.of(userEntity.getEmail()),
            Password.of(userEntity.getPassword()),
            userEntity.getUserType(),
            WalletDbMapper.toDomain(userEntity.getWallet())
        );
    }

    public static UserEntity toEntity(final User user) {
        return new UserEntity(
            user.id(),
            user.name(),
            user.getDocumentNumber(),
            user.getEmailAddress(),
            user.password().key(),
            user.userType(),
            WalletDbMapper.toEntity(user.wallet())
        );
    }

}
