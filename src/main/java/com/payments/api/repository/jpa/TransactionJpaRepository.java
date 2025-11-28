package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
}
