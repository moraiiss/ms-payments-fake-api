package com.payments.api.repository.mapper;

import com.payments.api.core.domain.entities.Seller;
import com.payments.api.repository.jpa.entities.SellerEntity;

public final class SellerDbMapper {

    private SellerDbMapper() { }

    public static Seller toDomain(final SellerEntity sellerEntity) {
        return Seller.of(sellerEntity.getId(), sellerEntity.getSocialReason(), sellerEntity.getFantasyName(),
            sellerEntity.getDocument(), sellerEntity.getEmail(), sellerEntity.getPassword(),
            WalletDbMapper.toDomain(sellerEntity.getWallet()));
    }

    public static SellerEntity toEntity(final Seller seller) {
        return new SellerEntity(seller.id(), seller.socialReason(), seller.fantasyName(), seller.getDocumentNumber(),
            seller.getEmailAddress(), seller.getPassword(), WalletDbMapper.toEntity(seller.wallet()));
    }
}
