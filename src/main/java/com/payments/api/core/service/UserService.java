package com.payments.api.core.service;

import com.payments.api.core.domain.entities.User;
import com.payments.api.core.domain.exceptions.ExistingEmailException;
import com.payments.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(final User user) {

//        boolean hasUser = userRepository.findUserByEmail(user.getEmailAddress());
//
//        if (hasUser) {
//            throw new ExistingEmailException();
//        }

        return userRepository.create(user);
    }
}
