package com.payments.api.repository.mapper;

import com.payments.api.core.entities.payments.Wallet;
import com.payments.api.repository.jpa.entities.WalletEntity;

public final class WalletDbMapper {

    private WalletDbMapper() {}

    public static WalletEntity toEntity(final Wallet wallet) {
        return WalletEntity.of(wallet.getBalance());
    }

    public static Wallet toDomain(final WalletEntity entity) {
        return Wallet.of(entity.getId(), entity.getBalance());
    }

}
