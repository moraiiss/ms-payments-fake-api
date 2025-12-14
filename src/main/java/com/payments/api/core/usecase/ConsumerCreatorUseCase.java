package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.core.service.UserValidatorService;
import com.payments.api.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumerCreatorUseCase {

    private final ConsumerRepository consumerRepository;

    private final UserValidatorService userValidatorService;

    public ConsumerCreatorUseCase(final ConsumerRepository consumerRepository,
                                  final UserValidatorService userValidatorService) {
        this.consumerRepository = consumerRepository;
        this.userValidatorService = userValidatorService;
    }

    public Consumer create(final Consumer consumer) {

        userValidatorService.validateConsumerDocument(consumer.getDocumentNumber());

        userValidatorService.validateEmail(consumer.getEmailAddress());

        return consumerRepository.create(consumer);
    }
}
