package com.payments.api.usecases;

import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerUseCase {

    private final ConsumerRepository repository;

    public ConsumerUseCase(final ConsumerRepository repository) {
        this.repository = repository;
    }

    public List<Consumer> listConsumers() {
        return repository.findAll();
    }

    public Long createConsumer(Consumer consumer) {

        boolean hasConsumerDocument = this.findConsumerByDocument(consumer);

        if (hasConsumerDocument) {
            throw new IllegalArgumentException("Already exists a consumer with this document");
        }

        boolean hasConsumerEmail = this.findConsumerByEmail(consumer);

        if (hasConsumerEmail) {
            throw new IllegalArgumentException("Already exists a consumer with this email");
        }

        return repository.save(consumer);
    }

    private boolean findConsumerByDocument(Consumer consumer) {
        return repository.findByDocument(consumer).isPresent();
    }

    private boolean findConsumerByEmail(Consumer consumer) {
        return repository.findByEmail(consumer)
            .isPresent();
    }
}
