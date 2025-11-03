package com.payments.api.repository.jpa.entities;

import com.payments.api.core.entities.identity.Consumer;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "wallets")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")
    private ConsumerEntity consumer;

    private double balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public WalletEntity() {}

    private WalletEntity(final double balance, final LocalDateTime createdAt, final ConsumerEntity consumer) {
        this.balance = balance;
        this.createdAt = createdAt;
        this.consumer = consumer;
    }

    public static WalletEntity withBalanceZero(final ConsumerEntity consumer) {
        return new WalletEntity(0, LocalDateTime.now(), consumer);
    }
}
