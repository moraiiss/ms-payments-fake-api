package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetConsumerUseCase {

    private final UserRepository userRepository;

    public GetConsumerUseCase(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Consumer> getAll() {
        return userRepository.findAll();
    }
}
