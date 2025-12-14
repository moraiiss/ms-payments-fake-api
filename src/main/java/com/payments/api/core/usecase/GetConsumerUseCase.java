package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetConsumerUseCase {

    private final ConsumerRepository consumerRepository;

    public GetConsumerUseCase(final ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    public List<Consumer> getAll() {
        return consumerRepository.findAll();
    }
}
