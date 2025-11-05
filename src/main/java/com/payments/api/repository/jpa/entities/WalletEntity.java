package com.payments.api.repository.jpa.entities;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private SellerEntity seller;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PostPersist
    protected  void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public WalletEntity() {}

    private WalletEntity(final double balance, final LocalDateTime createdAt, final ConsumerEntity consumer,
                         final SellerEntity seller) {
        this.balance = balance;
        this.createdAt = createdAt;
        this.consumer = consumer;
        this.seller = seller;
    }

    public static WalletEntity of(final ConsumerEntity consumer) {
        return new WalletEntity(0, LocalDateTime.now(), consumer, null);
    }

    public static WalletEntity of(final SellerEntity seller) {
        return new WalletEntity(0, LocalDateTime.now(), null, seller);
    }
}
