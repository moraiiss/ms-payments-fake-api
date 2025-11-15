package com.payments.api.core.service;

import com.payments.api.repository.ConsumerRepository;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

// todo -> core domain "falando" com a camada repository ?
@Service
public class DuplicateValidationService {

    private final SellerRepository sellerRepository;

    private final ConsumerRepository consumerRepository;

    public DuplicateValidationService(final SellerRepository sellerRepository, final ConsumerRepository consumerRepository) {
        this.sellerRepository = sellerRepository;
        this.consumerRepository = consumerRepository;
    }

    public void isEmailValid(String emailAddress) {
        boolean sellerHasEmail = sellerRepository
            .findByEmail(emailAddress)
            .isPresent();

        boolean consumerHasEmail = consumerRepository
            .findByEmail(emailAddress)
            .isPresent();

        if (sellerHasEmail || consumerHasEmail) {
            throw new IllegalArgumentException("There is already a user with this email.");
        }
    }
}
