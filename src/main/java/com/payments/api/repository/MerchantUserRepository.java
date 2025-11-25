package com.payments.api.repository;

import com.payments.api.core.domain.identity.MerchantUser;
import com.payments.api.repository.jpa.MerchantUserJpaRepository;
import com.payments.api.repository.jpa.entities.MerchantUserEntity;
import com.payments.api.repository.mapper.MerchantUserDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MerchantUserRepository {

    private final MerchantUserJpaRepository merchantUserJpaRepository;

    public List<MerchantUser> findAll() {
        return merchantUserJpaRepository.findAllWithUserAndWallet()
            .stream()
            .map(MerchantUserDbMapper::toDomain)
            .toList();
    }

    public MerchantUser save(MerchantUser merchantUser) {
        MerchantUserEntity entity = merchantUserJpaRepository
            .save(MerchantUserDbMapper.toEntity(merchantUser));

        return MerchantUserDbMapper.toDomain(entity);
    }

    public boolean findMerchantUserByDocument(final String document) {
        MerchantUserEntity merchantUser = merchantUserJpaRepository.findByDocument(document)
            .orElse(null);

        return merchantUser != null;
    }

}
