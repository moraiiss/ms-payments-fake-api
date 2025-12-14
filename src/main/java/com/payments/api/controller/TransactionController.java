package com.payments.api.controller;

import com.payments.api.core.domain.entities.Transaction;
import com.payments.api.core.usecase.TransactionCreatorUseCase;
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

    private final TransactionCreatorUseCase transactionCreatorUseCase;

    @PostMapping
    public ResponseEntity<Transaction> store(@RequestBody Transaction requestTransaction) {
        var transaction = transactionCreatorUseCase.create(requestTransaction);

        return ResponseEntity.ok(transaction);
    }
}
