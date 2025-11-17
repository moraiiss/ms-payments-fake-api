package com.payments.api.repository;

import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.repository.jpa.ConsumerJpaRepository;
import com.payments.api.repository.jpa.WalletJpaRepository;
import com.payments.api.repository.jpa.entities.ConsumerEntity;
import com.payments.api.repository.jpa.entities.WalletEntity;
import com.payments.api.repository.mapper.ConsumerDbMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsumerRepository {

    private final ConsumerJpaRepository consumerJpaRepository;

    private final WalletJpaRepository walletJpaRepository;

    public ConsumerRepository(final ConsumerJpaRepository consumerJpaRepository,
                              final WalletJpaRepository walletJpaRepository) {
        this.consumerJpaRepository = consumerJpaRepository;
        this.walletJpaRepository = walletJpaRepository;
    }

    public List<Consumer> findAll() {
        List<ConsumerEntity> consumers = consumerJpaRepository.findAll();

        // todo + sobre streams
        return consumers
            .stream()
            .map(ConsumerDbMapper::toDomain)
            .toList();
    }

    public Long save(Consumer consumer) {
        ConsumerEntity consumerEntity = ConsumerDbMapper.toEntity(consumer);

        WalletEntity wallet = WalletEntity.of(consumerEntity);
        walletJpaRepository.save(wallet);

        ConsumerEntity savedConsumer = consumerJpaRepository.save(consumerEntity);

        return savedConsumer.getId();
    }

    public Optional<Consumer> findByDocument(String document) {
        return consumerJpaRepository
            .findByDocument(document)
            .map(ConsumerDbMapper::toDomain);
    }

    public Optional<Consumer> findByEmail(String email) {
        return consumerJpaRepository
            .findByEmail(email)
            .map(ConsumerDbMapper::toDomain);
    }

    public Consumer findById(Long id) {
        return consumerJpaRepository
            .findById(id)
            .map(ConsumerDbMapper::toDomain)
            .orElse(null);
    }
}
