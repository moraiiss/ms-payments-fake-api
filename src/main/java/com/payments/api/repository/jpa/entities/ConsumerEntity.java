package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
}
