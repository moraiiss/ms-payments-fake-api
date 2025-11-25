package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.MerchantUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MerchantUserJpaRepository extends JpaRepository<MerchantUserEntity, Long> {

    @Query("SELECT c FROM MerchantUserEntity c JOIN FETCH c.user u JOIN FETCH u.wallet")
    List<MerchantUserEntity> findAllWithUserAndWallet();

    Optional<MerchantUserEntity> findByDocument(String document);

}
