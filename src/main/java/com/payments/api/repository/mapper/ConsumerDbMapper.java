package com.payments.api.repository.mapper;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.repository.jpa.entities.ConsumerEntity;

public final class ConsumerDbMapper {

    private ConsumerDbMapper() { }

    public static Consumer toDomain(final ConsumerEntity consumerEntity) {
        return Consumer.of(consumerEntity.getId(), consumerEntity.getName(), consumerEntity.getDocument(),
            consumerEntity.getEmail(), consumerEntity.getPassword(),
            WalletDbMapper.toDomain(consumerEntity.getWallet()));
    }

    public static ConsumerEntity toEntity(final Consumer consumer) {
        return new ConsumerEntity(consumer.id(), consumer.name(), consumer.getDocumentNumber(),
            consumer.getEmailAddress(), consumer.getPasswordKey(), WalletDbMapper.toEntity(consumer.wallet()));
    }

}
