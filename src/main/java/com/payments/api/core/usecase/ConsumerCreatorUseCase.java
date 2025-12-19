package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.identity.Consumer;
import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.ports.input.ConsumerCreatorPort;
import com.payments.api.core.ports.output.ConsumerRepositoryPort;
import com.payments.api.core.service.UserValidatorService;
import org.springframework.stereotype.Service;

@Service
public class ConsumerCreatorUseCase implements ConsumerCreatorPort {

    private final ConsumerRepositoryPort consumerRepositoryPort;

    private final UserValidatorService userValidatorService;

    public ConsumerCreatorUseCase(final ConsumerRepositoryPort consumerRepositoryPort,
                                  final UserValidatorService userValidatorService) {
        this.consumerRepositoryPort = consumerRepositoryPort;
        this.userValidatorService = userValidatorService;
    }

    @Override
    public Consumer create(final Consumer consumer) {

        boolean hasDocument = consumerRepositoryPort.findConsumerByDocument(consumer.getDocumentNumber());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        userValidatorService.validateEmail(consumer.getEmailAddress());

        return consumerRepositoryPort.create(consumer);
    }
}
