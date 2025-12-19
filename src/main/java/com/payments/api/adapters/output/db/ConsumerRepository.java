package com.payments.api.adapters.output.db;

import com.payments.api.adapters.output.db.jpa.ConsumerJpaRepository;
import com.payments.api.adapters.output.db.jpa.entities.ConsumerEntity;
import com.payments.api.adapters.output.db.mapper.ConsumerDbMapper;
import com.payments.api.core.domain.entities.identity.Consumer;
import com.payments.api.core.ports.output.ConsumerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsumerRepository implements ConsumerRepositoryPort {

    private final ConsumerJpaRepository consumerJpaRepository;

    @Override
    public Consumer create(final Consumer consumer) {
        ConsumerEntity userEntity = consumerJpaRepository
            .save(ConsumerDbMapper.toEntity(consumer));

        return ConsumerDbMapper.toDomain(userEntity);
    }

    @Override
    public boolean findConsumerByEmail(final String email) {
        ConsumerEntity user = consumerJpaRepository.findByEmail(email)
            .orElse(null);

        return user != null;
    }

    @Override
    public boolean findConsumerByDocument(final String documentNumber) {
        ConsumerEntity user = consumerJpaRepository.findByDocument(documentNumber)
            .orElse(null);

        return user != null;
    }

    @Override
    public Consumer findConsumerById(final Long id) {
        Optional<ConsumerEntity> consumer = consumerJpaRepository.findById(id);

        return consumer
            .map(ConsumerDbMapper::toDomain)
            .orElse(null);
    }
}
