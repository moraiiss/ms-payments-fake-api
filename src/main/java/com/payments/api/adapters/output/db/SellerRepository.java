package com.payments.api.adapters.output.db;

import com.payments.api.core.domain.entities.identity.Seller;
import com.payments.api.adapters.output.db.jpa.SellerJpaRepository;
import com.payments.api.adapters.output.db.jpa.entities.SellerEntity;
import com.payments.api.adapters.output.db.mapper.SellerDbMapper;
import com.payments.api.core.ports.output.SellerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SellerRepository implements SellerRepositoryPort {

    private final SellerJpaRepository sellerJpaRepository;

    @Override
    public Seller create(final Seller seller) {
        SellerEntity newSeller = sellerJpaRepository
            .save(SellerDbMapper.toEntity(seller));

        return SellerDbMapper.toDomain(newSeller);
    }

    @Override
    public boolean findSellerByDocument(final String documentNumber) {
        SellerEntity user = sellerJpaRepository.findByDocument(documentNumber)
            .orElse(null);

        return user != null;
    }

    @Override
    public boolean findSellerByEmail(final String email) {
        SellerEntity seller = sellerJpaRepository.findByEmail(email)
            .orElse(null);

        return seller != null;
    }

    @Override
    public Seller findSellerById(final Long id) {
        Optional<SellerEntity> seller = sellerJpaRepository.findById(id);

        return seller
            .map(SellerDbMapper::toDomain)
            .orElse(null);

    }
}
