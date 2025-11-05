package com.payments.api.controller;

import com.payments.api.controller.dto.SellerRequestDto;
import com.payments.api.controller.mapper.SellerRestMapper;
import com.payments.api.usecases.SellerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
@Tag(name = "Sellers", description = "Endpoints for manager consumers data")
public class SellerController {

    private final SellerUseCase sellerUseCase;

    public SellerController(final SellerUseCase sellerUseCase) {
        this.sellerUseCase = sellerUseCase;
    }

    @PostMapping
    @Operation(
        summary = "Create a new seller",
        description = "Create a new seller with the provided information"
    )
    public ResponseEntity<String> create(@RequestBody SellerRequestDto sellerRequestDto) {
        Long sellerId = sellerUseCase
            .createSeller(SellerRestMapper.toDomain(sellerRequestDto));

        return ResponseEntity.status(HttpStatus.CREATED).body("Seller created with ID " + sellerId);
    }
}
