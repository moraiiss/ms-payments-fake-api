package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.PaymentRequestDto;
import com.payments.api.core.entities.payments.Payment;

public final class PaymentRestMapper {

    private PaymentRestMapper() {}

    public static Payment toDomain(final PaymentRequestDto requestDto) {
        return Payment.of(requestDto.payer(), requestDto.payee(), requestDto.amount());
    }

}
