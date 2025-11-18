package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    private Long payer;

    private Long payee;

    public PaymentEntity() {
    }

    public PaymentEntity(final Long transactionId, final LocalDateTime transactionDate, final Long payer,
                         final Long payee) {

        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
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

    public Long getPayer() {
        return payer;
    }

    public Long getPayee() {
        return payee;
    }
}
