package com.payments.api.repository;

import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.repository.jpa.ConsumerJpaRepository;
import com.payments.api.repository.jpa.entities.ConsumerEntity;
import com.payments.api.repository.jpa.entities.WalletEntity;
import com.payments.api.repository.mapper.ConsumerDbMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsumerRepository {

    private final ConsumerJpaRepository jpaRepository;

    public ConsumerRepository(final ConsumerJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<Consumer> findAll() {
        List<ConsumerEntity> consumers = jpaRepository.findAll();

        // TODO streams
        return consumers
            .stream()
            .map(ConsumerDbMapper::toDomain)
            .toList();
    }

    public Long save(Consumer consumer) {
        WalletEntity wallet = WalletEntity.withBalanceZero();

        ConsumerEntity consumerEntity = ConsumerDbMapper.toEntity(consumer);
        consumerEntity.setWallet(wallet);

        ConsumerEntity savedConsumer = jpaRepository.save(consumerEntity);

        return savedConsumer.getId();
    }

    public Optional<Consumer> findByDocument(Consumer consumer) {
        return jpaRepository
            .findByDocument(consumer.getDocument())
            .map(ConsumerDbMapper::toDomain);
    }
}
