package com.payments.api.controller;

import com.payments.api.controller.dto.PaymentRequestDto;
import com.payments.api.controller.mapper.PaymentRestMapper;
import com.payments.api.core.entities.payments.Payment;
import com.payments.api.usecase.PaymentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    //todo + sobre o AutoWired
    private final PaymentUseCase paymentUseCase;

    public PaymentController(final PaymentUseCase paymentUseCase) {
        this.paymentUseCase = paymentUseCase;
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody PaymentRequestDto paymentRequestDto) {
        Payment payment = PaymentRestMapper.toDomain(paymentRequestDto);

        paymentUseCase.process(payment);

        return ResponseEntity.ok(payment);
    }
}
