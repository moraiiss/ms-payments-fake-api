package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "wallets")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "wallet")
    private ConsumerEntity consumers;

    private double balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public WalletEntity() {}

    private WalletEntity(final double balance, final LocalDateTime createdAt) {
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public static WalletEntity withBalanceZero() {
        return new WalletEntity(0, LocalDateTime.now());
    }
}
