package com.payments.api.adapters.input.rest.mapper;

import com.payments.api.adapters.input.rest.dto.ConsumerRequestDto;
import com.payments.api.adapters.input.rest.dto.ConsumerResponseDto;
import com.payments.api.core.domain.entities.identity.Consumer;

public class ConsumerRestMapper {

    private ConsumerRestMapper() { }

    public static ConsumerResponseDto toDto(final Consumer consumer) {
        return new ConsumerResponseDto(consumer.id(), consumer.name(), consumer.getDocumentNumber(),
            consumer.getEmailAddress());
    }

    public static Consumer toDomain(final ConsumerRequestDto requestDto) {
        return Consumer.of(requestDto.name(), requestDto.document(), requestDto.email());
    }

}
