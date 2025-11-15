package com.payments.api.controller;

import com.payments.api.controller.dto.ConsumerRequestDto;
import com.payments.api.controller.dto.ConsumerResponseDto;
import com.payments.api.controller.mapper.ConsumerRestMapper;
import com.payments.api.usecase.ConsumerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Consumers", description = "Endpoints for manager consumers data")
@RequestMapping("consumers")
public class ConsumerController {

    private final ConsumerUseCase service;

    public ConsumerController(final ConsumerUseCase consumerService) {
        this.service = consumerService;
    }

    // TODO sobre ResponseEntity
    @GetMapping
    @Operation(
        summary = "List all consumers",
        description = "Retrieve a list of all consumers in the system"
    )
    public ResponseEntity<List<ConsumerResponseDto>> index() {
        List<ConsumerResponseDto> consumers = service.listConsumers()
            .stream()
            .map(ConsumerRestMapper::toDto)
            .toList();

        return ResponseEntity.ok(consumers);
    }

    @PostMapping
    @Operation(
        summary = "Create a new consumer",
        description = "Create a new consumer with the provided information"
    )
    public ResponseEntity<String> create(@RequestBody ConsumerRequestDto requestBody) {
        Long consumerId = service
            .createConsumer(ConsumerRestMapper.toDomain(requestBody));

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Consumer created with ID " + consumerId);
    }
}
