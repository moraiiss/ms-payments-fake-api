package com.payments.api.core.service;

import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.domain.exceptions.ExistingEmailException;
import com.payments.api.repository.ConsumerRepository;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorService {

    private final SellerRepository sellerRepository;

    private final ConsumerRepository consumerRepository;

    public UserValidatorService(final SellerRepository sellerRepository, final ConsumerRepository consumerRepository) {
        this.sellerRepository = sellerRepository;
        this.consumerRepository = consumerRepository;
    }

    public void validateEmail(final String emailAddress) {

        boolean hasSellerEmail = sellerRepository.findSellerByEmail(emailAddress);
        boolean hasConsumerEmail = consumerRepository.findUserByEmail(emailAddress);

        if (hasSellerEmail || hasConsumerEmail) {
            throw new ExistingEmailException();
        }
    }

    public void validateSellerDocument(final String document) {

        boolean hasDocument = sellerRepository.findSellerByDocument(document);

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

    }

    public void validateConsumerDocument(final String document) {

        boolean hasDocument = consumerRepository.findConsumerByDocument(document);

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

    }
}
