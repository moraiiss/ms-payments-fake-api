package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.core.domain.entities.Transaction;
import com.payments.api.core.domain.exceptions.TransactionException;
import com.payments.api.core.service.WalletService;
import com.payments.api.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionCreatorUseCase {

    private final ConsumerRepository consumerRepository;

    private final WalletService walletService;

    public TransactionCreatorUseCase(final ConsumerRepository consumerRepository, final WalletService walletService) {
        this.consumerRepository = consumerRepository;
        this.walletService = walletService;
    }

    public Transaction process(Transaction transaction) {

        if (transaction.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("Transaction amount must be greater zero");
        }

        Consumer payer = consumerRepository.findUserById(transaction.payer());
        Consumer payee = consumerRepository.findUserById(transaction.payee());

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
