package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.ConsumerRequestDto;
import com.payments.api.core.entities.Consumer;

public final class ConsumerRestMapper {

    private ConsumerRestMapper() {}

    public static Consumer toDomain(final ConsumerRequestDto dto) {
        return Consumer.of(dto.name(), dto.document(), dto.email(), dto.password());
    }
}
