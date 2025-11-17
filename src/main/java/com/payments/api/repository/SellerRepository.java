package com.payments.api.repository;

import com.payments.api.core.entities.identity.Seller;
import com.payments.api.repository.jpa.SellerJpaRepository;
import com.payments.api.repository.jpa.WalletJpaRepository;
import com.payments.api.repository.jpa.entities.SellerEntity;
import com.payments.api.repository.jpa.entities.WalletEntity;
import com.payments.api.repository.mapper.SellerDbMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SellerRepository {

    private final SellerJpaRepository sellerJpaRepository;

    private final WalletJpaRepository walletJpaRepository;

    public SellerRepository(final SellerJpaRepository sellerJpaRepository, final WalletJpaRepository walletJpaRepository) {
        this.sellerJpaRepository = sellerJpaRepository;
        this.walletJpaRepository = walletJpaRepository;
    }

    public Long save(Seller seller) {
        SellerEntity sellerEntity = SellerDbMapper.toEntity(seller);

        var wallet = WalletEntity.of(sellerEntity);
        walletJpaRepository.save(wallet);

        SellerEntity createdSeller = sellerJpaRepository.save(sellerEntity);

        return createdSeller.getId();
    }

    public Optional<Seller> findSellerByDocument(String document) {
        return sellerJpaRepository.findByDocument(document)
            .map(SellerDbMapper::toDomain);
    }

    public Optional<Seller> findByEmail(String email) {
        return sellerJpaRepository.findByEmail(email)
            .map(SellerDbMapper::toDomain);
    }

    public Seller findById(Long id) {
        return sellerJpaRepository.findById(id)
            .map(SellerDbMapper::toDomain)
            .orElse(null);
    }
}
