package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.identity.Seller;
import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.ports.input.SellerCreatorPort;
import com.payments.api.core.ports.input.UserValidatorServicePort;
import com.payments.api.core.ports.output.SellerRepositoryPort;

public class SellerCreatorUseCase implements SellerCreatorPort {

    private final SellerRepositoryPort sellerRepositoryPort;

    private final UserValidatorServicePort userValidatorServicePort;

    public SellerCreatorUseCase(final SellerRepositoryPort sellerRepositoryPort,
                                final UserValidatorServicePort userValidatorService) {
        this.sellerRepositoryPort = sellerRepositoryPort;
        this.userValidatorServicePort = userValidatorService;
    }

    @Override
    public Seller create(final Seller seller) {

        boolean hasDocument = sellerRepositoryPort.findSellerByDocument(seller.getDocumentNumber());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        userValidatorServicePort.validateEmail(seller.getEmailAddress());

        return sellerRepositoryPort.create(seller);
    }
}
