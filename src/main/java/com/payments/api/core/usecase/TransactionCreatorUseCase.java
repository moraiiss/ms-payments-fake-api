package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Payee;
import com.payments.api.core.domain.entities.Payer;
import com.payments.api.core.domain.entities.Transaction;
import com.payments.api.core.domain.exceptions.NotFoundException;
import com.payments.api.core.domain.exceptions.TransactionException;
import com.payments.api.http.AuthorizationClient;
import com.payments.api.http.dto.AuthorizationResponseDto;
import com.payments.api.repository.ConsumerRepository;
import com.payments.api.repository.SellerRepository;
import com.payments.api.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionCreatorUseCase {

    private final ConsumerRepository consumerRepository;

    private final SellerRepository sellerRepository;

    private final WalletRepository walletRepository;

    private final AuthorizationClient authorizationClient;

    public TransactionCreatorUseCase(final ConsumerRepository consumerRepository, final SellerRepository sellerRepository,
                                     final WalletRepository walletRepository, final AuthorizationClient authorizationClient) {
        this.consumerRepository = consumerRepository;
        this.sellerRepository = sellerRepository;
        this.walletRepository = walletRepository;
        this.authorizationClient = authorizationClient;
    }

    public Transaction create(final Transaction transaction) {

        Payer payer = findPayer(transaction.payer());
        Payee payee = findPayee(transaction.payee());

        AuthorizationResponseDto isAuthorized = authorizationClient.authorize();

        if (!isAuthorized.authorized()) {
            throw new TransactionException("Transaction not authorized!");
        }

        walletRepository.save(payer.debit(transaction.amount()));
        walletRepository.save(payee.credit(transaction.amount()));

        return transaction;
    }

    private Payer findPayer(final Long payerId) {
        Payer payer = consumerRepository.findConsumerById(payerId);

        if (payer == null) {
            throw new NotFoundException("Payer not found");
        }

        return payer;
    }

    private Payee findPayee(final Long payeeId) {
        Payee payee = sellerRepository.findSellerById(payeeId);

        if (payee == null) {
            payee = consumerRepository.findConsumerById(payeeId);
        }

        if (payee == null) {
            throw new NotFoundException("Payee not found");
        }

        return payee;
    }
}
