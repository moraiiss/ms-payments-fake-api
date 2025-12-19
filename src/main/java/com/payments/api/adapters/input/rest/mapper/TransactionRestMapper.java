package com.payments.api.adapters.input.rest.mapper;

import com.payments.api.adapters.input.rest.dto.TransactionRequestDto;
import com.payments.api.core.domain.entities.payment.Transaction;

public class TransactionRestMapper {

    private TransactionRestMapper() { }

    public static Transaction toDomain(final TransactionRequestDto requestDto) {
        return null;
    }

}
