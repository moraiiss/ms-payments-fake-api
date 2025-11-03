package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

@Entity(name = "consumers")
public class ConsumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WalletEntity wallet;

    public ConsumerEntity() {
    }

    public ConsumerEntity(final String name, final String document, final String email, final String password) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
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

//    public void setWallet(WalletEntity wallet) {
//        this.wallet = wallet;
//    }
}
