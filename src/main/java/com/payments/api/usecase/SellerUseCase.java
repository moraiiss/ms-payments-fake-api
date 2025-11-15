package com.payments.api.usecase;

import com.payments.api.core.entities.identity.Seller;
import com.payments.api.core.service.DuplicateValidationService;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerUseCase {

    private final SellerRepository sellerRepository;

    private final DuplicateValidationService duplicateValidationService;

    public SellerUseCase(final SellerRepository sellerRepository,
                         final DuplicateValidationService duplicateValidationService) {
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

        this.duplicateValidationService.isEmailValid(seller.getEmail());

        return sellerRepository.save(seller);
    }

}
