package com.payments.api.usecases;

import com.payments.api.core.entities.identity.Seller;
import com.payments.api.core.service.DuplicateValidationService;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerUseCase {

    private final SellerRepository sellerRepository;

    private final DuplicateValidationService duplicateValidationService;

    public SellerUseCase(final SellerRepository sellerRepository, final DuplicateValidationService duplicateValidationService) {
        this.sellerRepository = sellerRepository;
        this.duplicateValidationService = duplicateValidationService;
    }

    public Long createSeller(Seller seller) {

        boolean hasDocument = sellerRepository
            .findSellerByDocument(seller.getDocument())
            .isPresent();

        if (hasDocument) {
            throw new IllegalArgumentException("There is already a seller with this document.");
        }

        boolean emailIsValid = duplicateValidationService.isEmailValid(seller.getEmail());

        if (!emailIsValid) {
            throw new IllegalArgumentException("There is already a user with this email.");
        }

        return sellerRepository.save(seller);
    }

}
