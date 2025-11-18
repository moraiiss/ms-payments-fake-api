package com.payments.api.repository.mapper;

import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.repository.jpa.entities.ConsumerEntity;

public final class ConsumerDbMapper {

    private ConsumerDbMapper() {}

    public static Consumer toDomain(ConsumerEntity entity) {
        return Consumer.of(entity.getId(), entity.getName(), entity.getDocument(), entity.getEmail(),
            entity.getPassword(), WalletDbMapper.toDomain(entity.getWallet()));
    }

    public static ConsumerEntity toEntity(Consumer consumer) {
        return new ConsumerEntity(consumer.getName(), consumer.getDocument(), consumer.getEmail(),
            consumer.getPassword(), WalletDbMapper.toEntity(consumer.getWallet()));
    }
}
