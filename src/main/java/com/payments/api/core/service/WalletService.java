package com.payments.api.core.service;

import com.payments.api.core.domain.entities.Wallet;
import com.payments.api.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(final WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void execute(final BigDecimal amount, final Wallet payer, final Wallet payee) {

        walletRepository.save(payer.debit(amount));

        walletRepository.save(payee.credit(amount));
    }
}
