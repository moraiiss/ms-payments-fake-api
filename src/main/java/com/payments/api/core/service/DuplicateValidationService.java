package com.payments.api.core.service;

import com.payments.api.repository.ConsumerRepository;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class DuplicateValidationService {

    private final SellerRepository sellerRepository;

    private final ConsumerRepository consumerRepository;

    public DuplicateValidationService(final SellerRepository sellerRepository, final ConsumerRepository consumerRepository) {
        this.sellerRepository = sellerRepository;
        this.consumerRepository = consumerRepository;
    }

    public boolean isEmailValid(String emailAddress) {
        boolean sellerHasEmail = sellerRepository
            .findByEmail(emailAddress)
            .isPresent();

        if (sellerHasEmail) return false;

        boolean consumerHasEmail = consumerRepository
            .findByEmail(emailAddress)
            .isPresent();

        return !consumerHasEmail;
    }
}
