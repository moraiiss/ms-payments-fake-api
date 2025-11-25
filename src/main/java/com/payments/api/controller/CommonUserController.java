package com.payments.api.controller;

import com.payments.api.controller.dto.request.CommonUserRequestDto;
import com.payments.api.controller.dto.response.CommonUserResponseDto;
import com.payments.api.controller.mapper.CommonUserRestMapper;
import com.payments.api.core.domain.identity.CommonUser;
import com.payments.api.core.usecase.CommonUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commons")
@RequiredArgsConstructor
public class CommonUserController {

    private final CommonUserUseCase commonUserUseCase;

    @GetMapping
    public ResponseEntity<List<CommonUserResponseDto>> index() {
        List<CommonUserResponseDto> commonUsers = commonUserUseCase.findAll()
            .stream()
            .map(CommonUserRestMapper::toDto)
            .toList();

        return ResponseEntity.ok(commonUsers);
    }

    @PostMapping
    public ResponseEntity<CommonUserResponseDto> store(@RequestBody CommonUserRequestDto commonUserRequestDto) {

        CommonUser commonUser = commonUserUseCase
            .create(CommonUserRestMapper.toDomain(commonUserRequestDto));

        CommonUserResponseDto responseDto = CommonUserRestMapper.toDto(commonUser);

        return ResponseEntity.ok(responseDto);
    }

}
