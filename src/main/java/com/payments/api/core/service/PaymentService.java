package com.payments.api.core.service;

import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.core.entities.identity.Seller;
import com.payments.api.core.entities.payments.Payee;
import com.payments.api.core.entities.payments.Payer;
import com.payments.api.core.exceptions.NotFoundException;
import com.payments.api.repository.ConsumerRepository;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final ConsumerRepository consumerRepository;

    private final SellerRepository sellerRepository;

    public PaymentService(final ConsumerRepository consumerRepository, final SellerRepository sellerRepository) {
        this.consumerRepository = consumerRepository;
        this.sellerRepository = sellerRepository;
    }

    public Payer findPayer(final Long payerId) {
        Consumer consumer = findOneConsumer(payerId);

        if (consumer == null) {
            throw new NotFoundException("Payer not found");
        }

        return consumer;
    }

    public Payee findPayee(final Long payeeId) {
        Seller seller = findOneSeller(payeeId);

        if (seller != null) {
            return seller;
        }

        Consumer consumer = findOneConsumer(payeeId);

        if (consumer != null) {
            return consumer;
        }

        throw new NotFoundException("Payee not found");
    }

    private Seller findOneSeller(final Long sellerId) {
        return sellerRepository
            .findById(sellerId);
    }

    private Consumer findOneConsumer(final Long consumerId) {
        return consumerRepository
            .findById(consumerId);
    }

}
