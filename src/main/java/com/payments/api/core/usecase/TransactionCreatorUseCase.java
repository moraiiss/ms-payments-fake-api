package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.core.domain.entities.Transaction;
import com.payments.api.core.domain.exceptions.TransactionException;
import com.payments.api.core.service.WalletService;
import com.payments.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionCreatorUseCase {

    private final UserRepository userRepository;

    private final WalletService walletService;

    public TransactionCreatorUseCase(final UserRepository userRepository, final WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    public Transaction process(Transaction transaction) {

        if (transaction.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("Transaction amount must be greater zero");
        }

        Consumer payer = userRepository.findUserById(transaction.payer());
        Consumer payee = userRepository.findUserById(transaction.payee());

//        if (payer.userType() == UserType.MERCHANT) {
//            throw new TransactionException("Payer can't be a merchant user!");
//        }

        if (payer.equals(payee)) {
            throw new TransactionException("Payer and payee can't be equals!");
        }

        walletService.execute(
            transaction.amount(),
            payer.wallet(),
            payee.wallet()
        );

        return transaction;
    }

}
