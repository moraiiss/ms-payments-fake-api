package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Seller;
import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.service.UserValidatorService;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerCreatorUseCase {

    private final SellerRepository sellerRepository;

    private final UserValidatorService userValidatorService;

    public SellerCreatorUseCase(final SellerRepository sellerRepository,
                                final UserValidatorService userValidatorService) {
        this.sellerRepository = sellerRepository;
        this.userValidatorService = userValidatorService;
    }

    public Seller create(final Seller seller) {

        boolean hasDocument = sellerRepository.findSellerByDocument(seller.getDocumentNumber());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        userValidatorService.validateEmail(seller.getEmailAddress());

        return sellerRepository.create(seller);
    }
}
