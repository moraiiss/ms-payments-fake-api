package com.payments.api.adapters.output.db.jpa;

import com.payments.api.adapters.output.db.jpa.entities.ConsumerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, Long> {

    Optional<ConsumerEntity> findByEmail(String email);

    Optional<ConsumerEntity> findByDocument(String document);

}
