package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.User;
import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.domain.exceptions.ExistingEmailException;
import com.payments.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCreatorUseCase {

    private final UserRepository userRepository;

    public UserCreatorUseCase(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User user) {

        boolean hasEmail = userRepository.findUserByEmail(user.getEmailAddress());

        if (hasEmail) {
            throw new ExistingEmailException();
        }

        boolean hasDocument = userRepository.findUserByDocument(user.getDocumentNumber());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        return userRepository.create(user);
    }

}
