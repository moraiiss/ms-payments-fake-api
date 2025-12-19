package com.payments.api.adapters.input.rest;

import com.payments.api.adapters.input.rest.dto.ConsumerRequestDto;
import com.payments.api.adapters.input.rest.dto.ConsumerResponseDto;
import com.payments.api.adapters.input.rest.mapper.ConsumerRestMapper;
import com.payments.api.core.domain.entities.identity.Consumer;
import com.payments.api.core.ports.input.ConsumerCreatorPort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consumers")
@Tag(name = "Consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerCreatorPort consumerCreatorPort;

    @PostMapping
    public ResponseEntity<ConsumerResponseDto> store(@RequestBody ConsumerRequestDto consumerRequestDto) {
        Consumer consumer = consumerCreatorPort.create(ConsumerRestMapper.toDomain(consumerRequestDto));

        return ResponseEntity.ok(ConsumerRestMapper.toDto(consumer));
    }

}
