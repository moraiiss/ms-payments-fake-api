package com.payments.api.repository;

import com.payments.api.core.domain.entities.Seller;
import com.payments.api.repository.jpa.SellerJpaRepository;
import com.payments.api.repository.jpa.entities.SellerEntity;
import com.payments.api.repository.mapper.SellerDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SellerRepository {

    private final SellerJpaRepository sellerJpaRepository;

    public Seller create(final Seller seller) {
        SellerEntity newSeller = sellerJpaRepository
            .save(SellerDbMapper.toEntity(seller));

        return SellerDbMapper.toDomain(newSeller);
    }

    public boolean findSellerByDocument(final String documentNumber) {
        SellerEntity user = sellerJpaRepository.findByDocument(documentNumber)
            .orElse(null);

        return user != null;
    }

    public boolean findSellerByEmail(final String email) {
        SellerEntity user = sellerJpaRepository.findByEmail(email)
            .orElse(null);

        return user != null;
    }
}
