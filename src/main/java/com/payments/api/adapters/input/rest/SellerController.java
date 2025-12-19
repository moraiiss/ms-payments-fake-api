package com.payments.api.adapters.input.rest;

import com.payments.api.adapters.input.rest.dto.SellerRequestDto;
import com.payments.api.adapters.input.rest.dto.SellerResponseDto;
import com.payments.api.adapters.input.rest.mapper.SellerRestMapper;
import com.payments.api.core.domain.entities.identity.Seller;
import com.payments.api.core.ports.input.SellerCreatorPort;
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

    private final SellerCreatorPort sellerCreatorPort;

    @PostMapping
    public ResponseEntity<SellerResponseDto> store(@RequestBody SellerRequestDto sellerRequestDto) {
        Seller seller = sellerCreatorPort
            .create(SellerRestMapper.toDomain(sellerRequestDto));

        return ResponseEntity.ok(SellerRestMapper.toDto(seller));
    }

}
