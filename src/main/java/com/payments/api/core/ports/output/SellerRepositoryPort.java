package com.payments.api.core.ports.output;

import com.payments.api.core.domain.entities.identity.Seller;

public interface SellerRepositoryPort {

    Seller create(final Seller seller);

    boolean findSellerByDocument(final String documentNumber);

    boolean findSellerByEmail(final String email);

    Seller findSellerById(final Long id);

}
