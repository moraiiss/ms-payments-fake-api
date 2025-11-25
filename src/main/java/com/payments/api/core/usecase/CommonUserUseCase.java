package com.payments.api.core.usecase;

import com.payments.api.core.domain.identity.CommonUser;
import com.payments.api.core.domain.identity.User;
import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.service.UserService;
import com.payments.api.repository.CommonUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonUserUseCase {

    private final UserService userService;

    private final CommonUserRepository commonUserRepository;

    public List<CommonUser> findAll() {
        return commonUserRepository.findAll();
    }

    @Transactional
    public CommonUser create(CommonUser commonUser) {

        boolean hasDocument = commonUserRepository
            .findCommonUserByDocument(commonUser.cpf());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        User user = userService.create(commonUser.user());

        return commonUserRepository.save(CommonUser.of(
            commonUser.name(),
            commonUser.document(),
            user
        ));
    }

}
