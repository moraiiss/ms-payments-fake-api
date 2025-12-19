package com.payments.api.adapters.output.db.mapper;

import com.payments.api.core.domain.entities.payment.Wallet;
import com.payments.api.adapters.output.db.jpa.entities.WalletEntity;

public final class WalletDbMapper {

    private WalletDbMapper() { }

    public static Wallet toDomain(final WalletEntity walletEntity) {
        return new Wallet(walletEntity.getId(), walletEntity.getBalance());
    }

    public static WalletEntity toEntity(final Wallet wallet) {
        return new WalletEntity(wallet.id(), wallet.balance());
    }

}
