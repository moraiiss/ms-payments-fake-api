package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.SellerRequestDto;
import com.payments.api.core.entities.identity.Seller;

public final class SellerRestMapper {

    private SellerRestMapper() {}

    public static Seller toDomain(SellerRequestDto dto) {
        return Seller.of(dto.socialReason(), dto.fantasyName(), dto.document(), dto.email(), dto.password());
    }
}
