package com.payments.api.core.ports.input;

import com.payments.api.core.domain.entities.identity.Consumer;

public interface ConsumerCreatorPort {

    Consumer create(final Consumer consumer);

}
