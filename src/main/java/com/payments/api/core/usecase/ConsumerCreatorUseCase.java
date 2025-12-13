package com.payments.api.core.usecase;

import com.payments.api.core.domain.entities.Consumer;
import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.domain.exceptions.ExistingEmailException;
import com.payments.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumerCreatorUseCase {

    private final UserRepository userRepository;

    public ConsumerCreatorUseCase(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Consumer create(final Consumer consumer) {

        boolean hasEmail = userRepository.findUserByEmail(consumer.getEmailAddress());

        if (hasEmail) {
            throw new ExistingEmailException();
        }

        boolean hasDocument = userRepository.findUserByDocument(consumer.getDocumentNumber());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        return userRepository.create(consumer);
    }

}
