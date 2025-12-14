package com.payments.api.repository;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.core.domain.exceptions.NotFoundException;
import com.payments.api.repository.jpa.ConsumerJpaRepository;
import com.payments.api.repository.jpa.entities.ConsumerEntity;
import com.payments.api.repository.mapper.ConsumerDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsumerRepository {

    private final ConsumerJpaRepository consumerJpaRepository;

    public Consumer create(final Consumer consumer) {
        ConsumerEntity userEntity = consumerJpaRepository
            .save(ConsumerDbMapper.toEntity(consumer));

        return ConsumerDbMapper.toDomain(userEntity);
    }

    public List<Consumer> findAll() {
        List<ConsumerEntity> userEntityList = consumerJpaRepository.findAll();

        return userEntityList.stream()
            .map(ConsumerDbMapper::toDomain)
            .toList();
    }

    public boolean findConsumerByEmail(final String email) {
        ConsumerEntity user = consumerJpaRepository.findByEmail(email)
            .orElse(null);

        return user != null;
    }

    public boolean findConsumerByDocument(final String documentNumber) {
        ConsumerEntity user = consumerJpaRepository.findByDocument(documentNumber)
            .orElse(null);

        return user != null;
    }

    public Consumer findConsumerById(final Long id) {
        Optional<ConsumerEntity> consumer = consumerJpaRepository.findById(id);

        return consumer
            .map(ConsumerDbMapper::toDomain)
            .orElse(null);
    }
}
