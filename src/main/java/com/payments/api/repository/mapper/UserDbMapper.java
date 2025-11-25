package com.payments.api.repository.mapper;

import com.payments.api.core.domain.identity.User;
import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;
import com.payments.api.repository.jpa.entities.UserEntity;

public final class UserDbMapper {

    private UserDbMapper() { }

    public static User toDomain(final UserEntity userEntity) {
        return new User(
            userEntity.getId(),
            WalletDbMapper.toDomain(userEntity.getWallet()),
            Email.of(userEntity.getEmail()),
            Password.of(userEntity.getPassword()),
            userEntity.getUserType()
        );
    }

    public static UserEntity toEntity(final User user) {
        return new UserEntity(
            user.id(),
            user.getEmailAddress(),
            user.getPasswordKey(),
            user.userType(),
            WalletDbMapper.toEntity(user.wallet()),
            null,
            null
        );
    }

}
