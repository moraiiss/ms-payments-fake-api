package com.payments.api.core.service;

import com.payments.api.core.domain.exceptions.ExistingEmailException;
import com.payments.api.core.ports.input.UserValidatorServicePort;
import com.payments.api.core.ports.output.ConsumerRepositoryPort;
import com.payments.api.core.ports.output.SellerRepositoryPort;

public class UserValidatorService implements UserValidatorServicePort {

    private final SellerRepositoryPort sellerRepository;

    private final ConsumerRepositoryPort consumerRepository;

    public UserValidatorService(final SellerRepositoryPort sellerRepository,
                                final ConsumerRepositoryPort consumerRepository) {
        this.sellerRepository = sellerRepository;
        this.consumerRepository = consumerRepository;
    }

    public void validateEmail(final String emailAddress) {

        boolean hasSellerEmail = sellerRepository.findSellerByEmail(emailAddress);
        boolean hasConsumerEmail = consumerRepository.findConsumerByEmail(emailAddress);

        if (hasSellerEmail || hasConsumerEmail) {
            throw new ExistingEmailException();
        }
    }
}
