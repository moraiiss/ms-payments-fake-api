package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// TODO uso do lombok
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
