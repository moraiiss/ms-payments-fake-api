package com.payments.api.repository.mapper;

import com.payments.api.core.entities.payments.Payment;
import com.payments.api.core.entities.payments.PaymentStatus;
import com.payments.api.repository.jpa.entities.PaymentEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public final class PaymentDbMapper {

    private PaymentDbMapper() {}

    public static PaymentEntity toEntity(Payment payment) {
        return new PaymentEntity(
            Math.abs(UUID.randomUUID().getMostSignificantBits()),
            LocalDateTime.now(),
            PaymentStatus.ON_HOLD,
            payment.getPayer(),
            payment.getPayee()
        );
    }

}
