package com.payments.api.usecases;

import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.core.service.DuplicateValidationService;
import com.payments.api.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerUseCase {

    private final ConsumerRepository consumerRepository;

    private final DuplicateValidationService duplicateValidationService;

    public ConsumerUseCase(final ConsumerRepository repository,
                           final DuplicateValidationService duplicateValidationService) {
        this.consumerRepository = repository;
        this.duplicateValidationService = duplicateValidationService;
    }

    public List<Consumer> listConsumers() {
        return consumerRepository.findAll();
    }

    public Long createConsumer(Consumer consumer) {

        boolean hasDocument = consumerRepository
            .findByDocument(consumer.getDocument())
            .isPresent();

        if (hasDocument) {
            throw new IllegalArgumentException("There is already a consumer with this document.");
        }

        boolean emailIsValid = this.duplicateValidationService.isEmailValid(consumer.getEmail());

        if (!emailIsValid) {
            throw new IllegalArgumentException("There is already a user with this email.");
        }

        return consumerRepository.save(consumer);
    }
}
