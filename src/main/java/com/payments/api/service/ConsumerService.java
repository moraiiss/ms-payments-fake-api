package com.payments.api.service;

import com.payments.api.core.entities.Consumer;
import com.payments.api.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO diferença entre usaCase x Service
@Service
public class ConsumerService {

    private final ConsumerRepository repository;

    public ConsumerService(final ConsumerRepository repository) {
        this.repository = repository;
    }

    public List<Consumer> listConsumers() {
        return repository.findAll();
    }

    public Long createConsumer(Consumer consumer) {

        Optional<Consumer> foundConsumer = this.findConsumer(consumer);

        if (foundConsumer.isPresent()) {
            throw new IllegalArgumentException("Already exists a consumer with this document");
        }

        return repository.save(consumer);
    }

    private Optional<Consumer> findConsumer(Consumer consumer) {
        return repository.findByDocument(consumer);
    }
}
