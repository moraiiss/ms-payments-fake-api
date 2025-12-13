package com.payments.api.controller.mapper;

import com.payments.api.controller.dto.ConsumerRequestDto;
import com.payments.api.controller.dto.ConsumerResponseDto;
import com.payments.api.core.domain.entities.Consumer;

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
