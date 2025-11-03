package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, Long> {

    // TODO rever Optional
    Optional<ConsumerEntity> findByDocument(String document);

    Optional<ConsumerEntity> findByEmail(String email);

}
