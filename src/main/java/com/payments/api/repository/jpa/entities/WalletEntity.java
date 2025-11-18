package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "wallets")
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance;

    public WalletEntity() {}

    private WalletEntity(final BigDecimal balance) {
        this.balance = balance;
    }

    public static WalletEntity of(final BigDecimal balance) {
        return new WalletEntity(balance);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
