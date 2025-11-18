package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SellerJpaRepository extends JpaRepository<SellerEntity, Long> {

    Optional<SellerEntity> findByDocument(String document);

    Optional<SellerEntity> findByEmail(String email);

}
