package com.payments.api.repository.mapper;

import com.payments.api.core.entities.identity.Seller;
import com.payments.api.repository.jpa.entities.SellerEntity;

public final class SellerDbMapper {

    private SellerDbMapper() {}

    public static Seller toDomain(SellerEntity entity) {
        return Seller.of(entity.getName(), entity.getDocument(), entity.getEmail(), entity.getPassword());
    }

    public static SellerEntity toEntity(Seller seller) {
        return new SellerEntity(seller.getName(), seller.getDocument(), seller.getEmail(), seller.getPassword());
    }

}
