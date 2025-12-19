package com.payments.api.core.ports.input;

import com.payments.api.core.domain.entities.identity.Seller;

public interface SellerCreatorPort {

    Seller create(final Seller seller);

}
