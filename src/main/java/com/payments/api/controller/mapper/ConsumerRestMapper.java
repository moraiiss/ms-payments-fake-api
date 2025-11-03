package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.ConsumerRequestDto;
import com.payments.api.controller.dto.ConsumerResponseDto;
import com.payments.api.core.entities.identity.Consumer;

public final class ConsumerRestMapper {

    private ConsumerRestMapper() {}

    public static Consumer toDomain(final ConsumerRequestDto dto) {
        return Consumer.of(dto.name(), dto.document(), dto.email(), dto.password());
    }

    public static ConsumerResponseDto toDto(final Consumer consumer) {
        return new ConsumerResponseDto(consumer.getName(), consumer.getDocument(), consumer.getEmail());
    }
}
