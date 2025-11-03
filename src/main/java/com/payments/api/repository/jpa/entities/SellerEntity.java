package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

// TODO uso do lombok
@Entity(name = "sellers")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String socialReason;

    private String fantasyName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
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

    public void setWallet(WalletEntity wallet) {
        this.wallet = wallet;
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
