package com.payments.api.controller;

import com.payments.api.controller.dto.SellerRequestDto;
import com.payments.api.controller.dto.SellerResponseDto;
import com.payments.api.controller.mapper.SellerRestMapper;
import com.payments.api.core.domain.entities.Seller;
import com.payments.api.core.usecase.SellerCreatorUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sellers")
@Tag(name = "Seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerCreatorUseCase sellerCreatorUseCase;

    @PostMapping
    public ResponseEntity<SellerResponseDto> store(@RequestBody SellerRequestDto sellerRequestDto) {
        Seller seller = sellerCreatorUseCase
            .create(SellerRestMapper.toDomain(sellerRequestDto));

        return ResponseEntity.ok(SellerRestMapper.toDto(seller));
    }

}
