package com.payments.api.usecase;

import com.payments.api.core.entities.identity.Consumer;
import com.payments.api.core.entities.identity.Seller;
import com.payments.api.core.entities.payments.Payee;
import com.payments.api.core.entities.payments.Payer;
import com.payments.api.core.entities.payments.Payment;
import com.payments.api.core.service.PaymentService;
import com.payments.api.repository.ConsumerRepository;
import com.payments.api.repository.PaymentRepository;
import com.payments.api.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentUseCase {

    private final PaymentService paymentService;

    private final ConsumerRepository consumerRepository;

    private final SellerRepository sellerRepository;

    private final PaymentRepository paymentRepository;

    public PaymentUseCase(final PaymentService paymentService, final ConsumerRepository consumerRepository,
                          final SellerRepository sellerRepository, final PaymentRepository paymentRepository) {
        this.paymentService = paymentService;
        this.consumerRepository = consumerRepository;
        this.sellerRepository = sellerRepository;
        this.paymentRepository = paymentRepository;
    }

    public void process(Payment payment) {

        Payer payer = paymentService.findPayer(payment.getPayer());
        Payee payee = paymentService.findPayee(payment.getPayee());

        payer.debit(payment.getAmount());
        payee.credit(payment.getAmount());

        consumerRepository.save((Consumer) payer);

        if (payee instanceof Consumer) {
            consumerRepository.save((Consumer) payee);
            return;
        }

        sellerRepository.save((Seller) payee);

        // persiste o pagamento
        paymentRepository.save(payment);
    }





}
