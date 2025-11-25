package com.payments.api.repository.mapper;

import com.payments.api.core.domain.payment.Wallet;
import com.payments.api.repository.jpa.entities.WalletEntity;

public final class WalletDbMapper {

    private WalletDbMapper() { }

    public static Wallet toDomain(final WalletEntity walletEntity) {
        return new Wallet(walletEntity.getId(), walletEntity.getBalance());
    }

    public static WalletEntity toEntity(final Wallet wallet) {
        return new WalletEntity(wallet.id(), wallet.balance());
    }

}
