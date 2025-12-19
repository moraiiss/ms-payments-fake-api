package com.payments.api.adapters.output.db.jpa;

import com.payments.api.adapters.output.db.jpa.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {
}
