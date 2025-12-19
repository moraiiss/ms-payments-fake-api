package com.payments.api.adapters.output.db.jpa;

import com.payments.api.adapters.output.db.jpa.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
}
