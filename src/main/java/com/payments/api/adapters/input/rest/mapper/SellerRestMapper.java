package com.payments.api.adapters.input.rest.mapper;

import com.payments.api.adapters.input.rest.dto.SellerRequestDto;
import com.payments.api.adapters.input.rest.dto.SellerResponseDto;
import com.payments.api.core.domain.entities.identity.Seller;

public final class SellerRestMapper {

    private SellerRestMapper() { }

    public static SellerResponseDto toDto(final Seller seller) {
        return new SellerResponseDto(seller.id(), seller.socialReason(), seller.fantasyName(),
            seller.getDocumentNumber(), seller.getEmailAddress());
    }

    public static Seller toDomain(final SellerRequestDto requestDto) {
        return Seller.of(requestDto.socialReason(), requestDto.fantasyName(), requestDto.document(),
            requestDto.email());
    }

}
