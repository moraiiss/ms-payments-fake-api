package com.payments.api.adapters.output.db;

import com.payments.api.core.domain.entities.payment.Wallet;
import com.payments.api.adapters.output.db.jpa.WalletJpaRepository;
import com.payments.api.adapters.output.db.mapper.WalletDbMapper;
import com.payments.api.core.ports.output.WalletRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WalletRepository implements WalletRepositoryPort {

    private final WalletJpaRepository walletJpaRepository;

    @Override
    public void save(Wallet wallet) {
        walletJpaRepository.save(WalletDbMapper.toEntity(wallet));
    }
}
