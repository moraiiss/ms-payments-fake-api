package com.payments.api.repository;

import com.payments.api.core.entities.payments.Payment;
import com.payments.api.repository.jpa.PaymentJpaRepository;
import com.payments.api.repository.jpa.entities.PaymentEntity;
import com.payments.api.repository.mapper.PaymentDbMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;

    public PaymentRepository(final PaymentJpaRepository paymentJpaRepository) {
        this.paymentJpaRepository = paymentJpaRepository;
    }

    public void save(Payment payment) {
        PaymentEntity entity = PaymentDbMapper.toEntity(payment);

        paymentJpaRepository.save(entity);
    }
}
