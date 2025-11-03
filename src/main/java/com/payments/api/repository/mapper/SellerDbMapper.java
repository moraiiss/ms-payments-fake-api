package com.payments.api.repository.mapper;

import com.payments.api.core.entities.identity.Seller;
import com.payments.api.repository.jpa.entities.SellerEntity;

public final class SellerDbMapper {

    private SellerDbMapper() {}

    public static Seller toDomain(SellerEntity entity) {
        return Seller.of(entity.getSocialReason(), entity.getFantasyName(), entity.getDocument(), entity.getEmail(),
            entity.getPassword());
    }

    public static SellerEntity toEntity(Seller seller) {
        return new SellerEntity(seller.getSocialReason(), seller.getFantasyName(), seller.getDocument(),
            seller.getEmail(), seller.getPassword());
    }

}
