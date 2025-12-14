package com.payments.api.repository;

import com.payments.api.core.domain.entities.Seller;
import com.payments.api.repository.jpa.SellerJpaRepository;
import com.payments.api.repository.jpa.entities.SellerEntity;
import com.payments.api.repository.mapper.SellerDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
        SellerEntity seller = sellerJpaRepository.findByEmail(email)
            .orElse(null);

        return seller != null;
    }

    public Seller findSellerById(final Long id) {
        Optional<SellerEntity> seller = sellerJpaRepository.findById(id);

        return seller
            .map(SellerDbMapper::toDomain)
            .orElse(null);

    }
}
