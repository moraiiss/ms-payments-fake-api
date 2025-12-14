package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerJpaRepository extends JpaRepository<SellerEntity, Long> {

    Optional<SellerEntity> findByEmail(String email);

    Optional<SellerEntity> findByDocument(String document);

}
