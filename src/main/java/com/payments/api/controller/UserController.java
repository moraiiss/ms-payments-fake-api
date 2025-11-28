package com.payments.api.controller;

import com.payments.api.controller.dto.UserRequestDto;
import com.payments.api.controller.dto.UserResponseDto;
import com.payments.api.controller.mapper.UserRestMapper;
import com.payments.api.core.domain.entities.User;
import com.payments.api.core.usecase.GetUserUseCase;
import com.payments.api.core.usecase.UserCreatorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUserUseCase getUseCase;

    private final UserCreatorUseCase creatorUseCase;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> index() {
        List<User> users = getUseCase.getAll();

        List<UserResponseDto> responseUsers = users.stream()
            .map(UserRestMapper::toDto)
            .toList();

        return ResponseEntity.ok(responseUsers);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> store(@RequestBody UserRequestDto userRequestDto) {
        User user = creatorUseCase.createUser(UserRestMapper.toDomain(userRequestDto));

        return ResponseEntity.ok(UserRestMapper.toDto(user));
    }

}
