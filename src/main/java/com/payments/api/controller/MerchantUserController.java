package com.payments.api.controller;

import com.payments.api.controller.dto.request.MerchantUserRequestDto;
import com.payments.api.controller.dto.response.MerchantUserResponseDto;
import com.payments.api.controller.mapper.MerchantUserRestMapper;
import com.payments.api.core.domain.identity.MerchantUser;
import com.payments.api.usecase.MerchantUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/merchants")
@RequiredArgsConstructor
public class MerchantUserController {

    private final MerchantUserUseCase merchantUserUseCase;

    @GetMapping
    public ResponseEntity<List<MerchantUserResponseDto>> index() {
        List<MerchantUserResponseDto> merchants = this.merchantUserUseCase.findAll()
            .stream()
            .map(MerchantUserRestMapper::toDto)
            .toList();

        return ResponseEntity.ok(merchants);
    }

    @PostMapping
    public ResponseEntity<MerchantUserResponseDto> store(@RequestBody MerchantUserRequestDto merchantUserRequestDto) {

        MerchantUser merchant = this.merchantUserUseCase
            .create(MerchantUserRestMapper.toDomain(merchantUserRequestDto));

        MerchantUserResponseDto responseDto = MerchantUserRestMapper.toDto(merchant);

        return ResponseEntity.ok(responseDto);
    }
}
