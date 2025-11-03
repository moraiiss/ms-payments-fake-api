package com.payments.api.controller;

import com.payments.api.controller.dto.ConsumerRequestDto;
import com.payments.api.controller.mapper.ConsumerRestMapper;
import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.usecases.ConsumerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consumers")
public class ConsumerController {

    private final ConsumerUseCase service;

    public ConsumerController(final ConsumerUseCase consumerService) {
        this.service = consumerService;
    }

    // TODO entender o ResponseEntity por debaixo dos panos
    @GetMapping
    public ResponseEntity<List<Consumer>> index() {
        return ResponseEntity.ok(service.listConsumers());
    }

    @PostMapping
    public ResponseEntity<String> create(
        @RequestBody ConsumerRequestDto requestBody
    ) {
        var consumerId = service
            .createConsumer(ConsumerRestMapper.toDomain(requestBody));

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Consumer created with ID " + consumerId);
    }
}
