package com.payments.api.repository.jpa.entities;

import com.payments.api.core.entities.payments.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // todo + sobre BigInteger
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private PaymentStatus paymentStatus;

    private Long payer;

    private Long payee;

    @Column(nullable = false, updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PostPersist
    protected  void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public PaymentEntity() {
    }

    public PaymentEntity(final Long transactionId, final LocalDateTime transactionDate,
                         final PaymentStatus paymentStatus, final Long payer, final Long payee) {

        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.paymentStatus = paymentStatus;
        this.payer = payer;
        this.payee = payee;
    }

    public Long getId() {
        return id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Long getPayer() {
        return payer;
    }

    public Long getPayee() {
        return payee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
