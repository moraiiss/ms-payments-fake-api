package com.payments.api.repository;

import com.payments.api.core.domain.entities.Wallet;
import com.payments.api.repository.jpa.WalletJpaRepository;
import com.payments.api.repository.mapper.WalletDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WalletRepository {

    private final WalletJpaRepository walletJpaRepository;

    public void save(Wallet wallet) {
        walletJpaRepository.save(WalletDbMapper.toEntity(wallet));
    }
}
