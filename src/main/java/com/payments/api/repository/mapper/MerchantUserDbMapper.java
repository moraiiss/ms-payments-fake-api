package com.payments.api.repository.mapper;

import com.payments.api.core.domain.identity.MerchantUser;
import com.payments.api.core.domain.vo.CNPJ;
import com.payments.api.repository.jpa.entities.MerchantUserEntity;

public final class MerchantUserDbMapper {

    private MerchantUserDbMapper() { }

    public static MerchantUser toDomain(final MerchantUserEntity merchantUserEntity) {
        return new MerchantUser(
            merchantUserEntity.getId(),
            UserDbMapper.toDomain(merchantUserEntity.getUser()),
            CNPJ.of(merchantUserEntity.getDocument()),
            merchantUserEntity.getFantasyName(),
            merchantUserEntity.getSocialReason()
        );
    }

    public static MerchantUserEntity toEntity(final MerchantUser merchantUser) {
        return MerchantUserEntity.builder()
            .id(merchantUser.id())
            .socialReason(merchantUser.socialReason())
            .fantasyName(merchantUser.fantasyName())
            .document(merchantUser.getDocumentNumber())
            .user(UserDbMapper.toEntity(merchantUser.user()))
            .build();
    }

}
