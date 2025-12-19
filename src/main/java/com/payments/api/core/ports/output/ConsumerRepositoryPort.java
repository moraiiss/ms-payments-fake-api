package com.payments.api.core.ports.output;

import com.payments.api.core.domain.entities.identity.Consumer;

public interface ConsumerRepositoryPort {

    Consumer create(final Consumer consumer);

    boolean findConsumerByEmail(final String email);

    boolean findConsumerByDocument(final String documentNumber);

    Consumer findConsumerById(final Long id);
}
