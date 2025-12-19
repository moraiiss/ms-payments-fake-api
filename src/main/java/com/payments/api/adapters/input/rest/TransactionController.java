package com.payments.api.adapters.input.rest;

import com.payments.api.core.domain.entities.payment.Transaction;
import com.payments.api.core.ports.input.TransactionCreatorPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionCreatorPort transactionCreatorPort;

    @PostMapping
    public ResponseEntity<Transaction> store(@RequestBody Transaction requestTransaction) {
        var transaction = transactionCreatorPort.create(requestTransaction);

        return ResponseEntity.ok(transaction);
    }
}
