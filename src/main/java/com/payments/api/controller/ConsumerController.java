package com.payments.api.controller;

import com.payments.api.controller.dto.ConsumerRequestDto;
import com.payments.api.controller.dto.ConsumerResponseDto;
import com.payments.api.controller.mapper.ConsumerRestMapper;
import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.core.usecase.GetConsumerUseCase;
import com.payments.api.core.usecase.ConsumerCreatorUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumers")
@Tag(name = "Consumers")
@RequiredArgsConstructor
public class ConsumerController {

    private final GetConsumerUseCase getUseCase;

    private final ConsumerCreatorUseCase consumerCreatorUseCase;

    @GetMapping
    public ResponseEntity<List<ConsumerResponseDto>> index() {
        List<Consumer> consumers = getUseCase.getAll();

        List<ConsumerResponseDto> responseUsers = consumers.stream()
            .map(ConsumerRestMapper::toDto)
            .toList();

        return ResponseEntity.ok(responseUsers);
    }

    @PostMapping
    public ResponseEntity<ConsumerResponseDto> store(@RequestBody ConsumerRequestDto consumerRequestDto) {
        Consumer consumer = consumerCreatorUseCase.create(ConsumerRestMapper.toDomain(consumerRequestDto));

        return ResponseEntity.ok(ConsumerRestMapper.toDto(consumer));
    }

}
