package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.User;
import com.payments.api.core.domain.entities.UserType;
import com.payments.api.core.domain.entities.Transaction;
import com.payments.api.core.domain.entities.Wallet;
import com.payments.api.repository.UserRepository;
import com.payments.api.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionCreatorUseCase {

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    public TransactionCreatorUseCase(final UserRepository userRepository, final WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public Transaction process(Transaction transaction) {

//        User payer = userRepository.findUserById(transaction.payer());
//        User payee = userRepository.findUserById(transaction.payee());
//
//        if (payer.userType() == UserType.MERCHANT) {
//            throw new IllegalArgumentException("Sender can't be a merchant user!");
//        }
//
//        if (payer.equals(payee)) {
//            throw new IllegalArgumentException("Sender and receiver can't be equals!");
//        }
//
//        Wallet payerWalletUpdated = payer.wallet().debit(transaction.amount());
//        Wallet payeeWalletUpdated = payee.wallet().credit(transaction.amount());
//
//        walletRepository.save(payerWalletUpdated);
//        walletRepository.save(payeeWalletUpdated);

        return transaction;
    }

}
