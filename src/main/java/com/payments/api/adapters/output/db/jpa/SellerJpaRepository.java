package com.payments.api.adapters.output.db.jpa;

import com.payments.api.adapters.output.db.jpa.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerJpaRepository extends JpaRepository<SellerEntity, Long> {

    Optional<SellerEntity> findByEmail(String email);

    Optional<SellerEntity> findByDocument(String document);

}
