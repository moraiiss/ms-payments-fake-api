package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.identity.Consumer;
import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.ports.input.ConsumerCreatorPort;
import com.payments.api.core.ports.input.UserValidatorServicePort;
import com.payments.api.core.ports.output.ConsumerRepositoryPort;

public class ConsumerCreatorUseCase implements ConsumerCreatorPort {

    private final ConsumerRepositoryPort consumerRepositoryPort;

    private final UserValidatorServicePort userValidatorServicePort;

    public ConsumerCreatorUseCase(final ConsumerRepositoryPort consumerRepositoryPort,
                                  final UserValidatorServicePort userValidatorServicePort) {
        this.consumerRepositoryPort = consumerRepositoryPort;
        this.userValidatorServicePort = userValidatorServicePort;
    }

    @Override
    public Consumer create(final Consumer consumer) {

        boolean hasDocument = consumerRepositoryPort.findConsumerByDocument(consumer.getDocumentNumber());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        userValidatorServicePort.validateEmail(consumer.getEmailAddress());

        return consumerRepositoryPort.create(consumer);
    }
}
