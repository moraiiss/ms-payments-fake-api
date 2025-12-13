package com.payments.api.repository;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.core.domain.exceptions.NotFoundException;
import com.payments.api.repository.jpa.ConsumerJpaRepository;
import com.payments.api.repository.jpa.entities.ConsumerEntity;
import com.payments.api.repository.mapper.ConsumerDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

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

    public boolean findUserByEmail(final String email) {
        ConsumerEntity user = consumerJpaRepository.findByEmail(email)
            .orElse(null);

        return user != null;
    }

    public boolean findUserByDocument(final String documentNumber) {
        ConsumerEntity user = consumerJpaRepository.findByDocument(documentNumber)
            .orElse(null);

        return user != null;
    }

    public Consumer findUserById(final Long id) {
        ConsumerEntity userEntity = consumerJpaRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found!"));

        return ConsumerDbMapper.toDomain(userEntity);
    }
}
