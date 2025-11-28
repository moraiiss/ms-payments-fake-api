package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {
}
