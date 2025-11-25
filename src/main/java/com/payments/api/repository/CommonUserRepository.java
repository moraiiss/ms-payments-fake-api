package com.payments.api.repository;

import com.payments.api.core.domain.identity.CommonUser;
import com.payments.api.repository.jpa.CommonUserJpaRepository;
import com.payments.api.repository.jpa.entities.CommonUserEntity;
import com.payments.api.repository.mapper.CommonUserDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommonUserRepository {

    private final CommonUserJpaRepository commonUserJpaRepository;

    public List<CommonUser> findAll() {
        return commonUserJpaRepository.findAllWithUserAndWallet()
            .stream()
            .map(CommonUserDbMapper::toDomain)
            .toList();
    }

    public CommonUser save(CommonUser commonUser) {
        CommonUserEntity entity = commonUserJpaRepository
            .save(CommonUserDbMapper.toEntity(commonUser));

        return CommonUserDbMapper.toDomain(entity);
    }

    public boolean findCommonUserByDocument(final String document) {
        CommonUserEntity commonUser = commonUserJpaRepository.findByDocument(document)
            .orElse(null);

        return commonUser != null;
    }
}
