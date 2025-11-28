package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.User;
import com.payments.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserUseCase {

    private final UserRepository userRepository;

    public GetUserUseCase(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
