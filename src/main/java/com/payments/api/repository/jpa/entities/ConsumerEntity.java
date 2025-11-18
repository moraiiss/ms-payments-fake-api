package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

@Entity(name = "consumers")
public class ConsumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String document;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String password;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletEntity wallet;

    public ConsumerEntity() {
    }

    public ConsumerEntity(final String name, final String document, final String email, final String password,
                          final WalletEntity wallet) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public WalletEntity getWallet() {
        return wallet;
    }
}
