package com.payments.api.repository.jpa;

import com.payments.api.repository.jpa.entities.CommonUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommonUserJpaRepository extends JpaRepository<CommonUserEntity, Long> {

    @Query("SELECT c FROM CommonUserEntity c JOIN FETCH c.user u JOIN FETCH u.wallet")
    List<CommonUserEntity> findAllWithUserAndWallet();

    Optional<CommonUserEntity> findByDocument(String document);

}
