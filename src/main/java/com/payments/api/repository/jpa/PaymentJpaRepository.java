package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long> {
}
