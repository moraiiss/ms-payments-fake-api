package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerJpaRepository extends JpaRepository<SellerEntity, Long> {
}
