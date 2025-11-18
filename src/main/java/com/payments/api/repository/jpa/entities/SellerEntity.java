package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

@Entity(name = "sellers")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String socialReason;

    private String fantasyName;

    @Column(unique = true, nullable = false)
    private String document;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletEntity wallet;

    public SellerEntity() {}

    public SellerEntity(final String socialReason, final String fantasyName, final String document, final String email,
                        final String password) {
        this.socialReason = socialReason;
        this.fantasyName = fantasyName;
        this.document = document;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public String getFantasyName() {
        return fantasyName;
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
}
