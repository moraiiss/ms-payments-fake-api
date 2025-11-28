package com.payments.api.repository.mapper;

import com.payments.api.core.domain.entities.User;
import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;
import com.payments.api.repository.jpa.entities.UserEntity;

public final class UserDbMapper {

    private UserDbMapper() { }

    public static User toDomain(final UserEntity userEntity) {
        return new User(
            userEntity.getId(),
            userEntity.getName(),
            userEntity.getDocument(),
            userEntity.getEmail(),
            Password.of(userEntity.getPassword()),
            userEntity.getUserType(),
            WalletDbMapper.toDomain(userEntity.getWallet())
        );
    }

    public static UserEntity toEntity(final User user) {
        return new UserEntity(
            user.id(),
            user.name(),
            user.document(),
            user.email(),
            user.password().key(),
            user.userType(),
            WalletDbMapper.toEntity(user.wallet())
        );
    }

}
