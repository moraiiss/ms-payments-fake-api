package com.payments.api.controller;

import com.payments.api.core.usecase.TransactionCreatorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionCreatorUseCase transactionCreatorUseCase;

}
