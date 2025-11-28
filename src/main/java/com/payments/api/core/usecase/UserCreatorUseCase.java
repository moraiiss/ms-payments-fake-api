package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.User;
import com.payments.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCreatorUseCase {

    private final UserRepository userRepository;

    public UserCreatorUseCase(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {
        return userRepository.create(user);
    }

}
