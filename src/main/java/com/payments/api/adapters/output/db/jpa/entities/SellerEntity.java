package com.payments.api.adapters.output.db.jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String socialReason;

    @Column(nullable = false)
    private String fantasyName;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private WalletEntity wallet;

}
