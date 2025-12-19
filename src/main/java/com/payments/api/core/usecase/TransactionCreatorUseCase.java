package com.payments.api.core.usecase;

import com.payments.api.adapters.output.feign.client.AuthorizationResponseDto;
import com.payments.api.core.domain.entities.identity.Payee;
import com.payments.api.core.domain.entities.identity.Payer;
import com.payments.api.core.domain.entities.payment.Transaction;
import com.payments.api.core.domain.exceptions.NotFoundException;
import com.payments.api.core.domain.exceptions.TransactionException;
import com.payments.api.core.ports.input.TransactionCreatorPort;
import com.payments.api.core.ports.output.AuthorizationClientPort;
import com.payments.api.core.ports.output.ConsumerRepositoryPort;
import com.payments.api.core.ports.output.SellerRepositoryPort;
import com.payments.api.core.ports.output.WalletRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class TransactionCreatorUseCase implements TransactionCreatorPort {

    private final ConsumerRepositoryPort consumerRepositoryPort;

    private final SellerRepositoryPort sellerRepositoryPort;

    private final WalletRepositoryPort walletRepositoryPort;

    private final AuthorizationClientPort authorizationClientPort;

    public TransactionCreatorUseCase(final ConsumerRepositoryPort consumerRepositoryPort,
                                     final SellerRepositoryPort sellerRepositoryPort,
                                     final WalletRepositoryPort walletRepositoryPort,
                                     final AuthorizationClientPort authorizationClientPort) {
        this.consumerRepositoryPort = consumerRepositoryPort;
        this.sellerRepositoryPort = sellerRepositoryPort;
        this.walletRepositoryPort = walletRepositoryPort;
        this.authorizationClientPort = authorizationClientPort;
    }

    // SRP violation
    @Override
    public Transaction create(final Transaction transaction) {

        Payer payer = findPayer(transaction.payer());
        Payee payee = findPayee(transaction.payee());

        AuthorizationResponseDto isAuthorized = authorizationClientPort.authorize();

        if (!isAuthorized.authorized()) {
            throw new TransactionException("Transaction not authorized!");
        }

        walletRepositoryPort.save(payer.debit(transaction.amount()));
        walletRepositoryPort.save(payee.credit(transaction.amount()));

        return transaction;
    }

    private Payer findPayer(final Long payerId) {
        Payer payer = consumerRepositoryPort.findConsumerById(payerId);

        if (payer == null) {
            throw new NotFoundException("Payer not found");
        }

        return payer;
    }

    private Payee findPayee(final Long payeeId) {
        Payee payee = sellerRepositoryPort.findSellerById(payeeId);

        if (payee == null) {
            payee = consumerRepositoryPort.findConsumerById(payeeId);
        }

        if (payee == null) {
            throw new NotFoundException("Payee not found");
        }

        return payee;
    }
}
