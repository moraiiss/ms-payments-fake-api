package com.payments.api.core.service;

import com.payments.api.adapters.output.db.ConsumerRepository;
import com.payments.api.adapters.output.db.SellerRepository;
import com.payments.api.core.domain.exceptions.ExistingEmailException;

public class UserValidatorService {

    private final SellerRepository sellerRepository;

    private final ConsumerRepository consumerRepository;

    public UserValidatorService(final SellerRepository sellerRepository, final ConsumerRepository consumerRepository) {
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
