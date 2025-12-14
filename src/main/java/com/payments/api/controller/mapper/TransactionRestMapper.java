package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.TransactionRequestDto;
import com.payments.api.core.domain.entities.Transaction;

public class TransactionRestMapper {

    private TransactionRestMapper() { }

    public static Transaction toDomain(final TransactionRequestDto requestDto) {
        return null;
    }

}
