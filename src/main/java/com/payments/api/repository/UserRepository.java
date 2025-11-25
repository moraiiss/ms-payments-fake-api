package com.payments.api.repository;

import com.payments.api.core.domain.identity.User;
import com.payments.api.repository.jpa.UserJpaRepository;
import com.payments.api.repository.jpa.entities.UserEntity;
import com.payments.api.repository.mapper.UserDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserJpaRepository userJpaRepository;

    public User create(final User user) {
        UserEntity userEntity = userJpaRepository
            .save(UserDbMapper.toEntity(user));

        return UserDbMapper.toDomain(userEntity);
    }

    public boolean findUserByEmail(final String email) {
        UserEntity user = userJpaRepository.findByEmail(email)
            .orElse(null);

        return user != null;
    }
}
