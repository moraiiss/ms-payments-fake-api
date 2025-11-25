package com.payments.api.repository.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "merchants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MerchantUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "social_reason")
    private String socialReason;

    @Column(nullable = false, name = "fantasy_name")
    private String fantasyName;

    @Column(nullable = false, unique = true)
    private String document;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity user;

}
