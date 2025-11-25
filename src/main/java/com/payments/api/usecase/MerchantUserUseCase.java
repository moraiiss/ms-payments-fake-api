package com.payments.api.usecase;

import com.payments.api.core.domain.exceptions.ExistingDocumentException;
import com.payments.api.core.domain.identity.MerchantUser;
import com.payments.api.core.domain.identity.User;
import com.payments.api.core.service.UserService;
import com.payments.api.repository.MerchantUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantUserUseCase {

    private final UserService userService;

    private final MerchantUserRepository merchantUserRepository;

    public List<MerchantUser> findAll() {
        return merchantUserRepository.findAll();
    }

    @Transactional
    public MerchantUser create(MerchantUser merchantUser) {

        boolean hasDocument = merchantUserRepository
            .findMerchantUserByDocument(merchantUser.getDocumentNumber());

        if (hasDocument) {
            throw new ExistingDocumentException();
        }

        User user = userService.create(merchantUser.user());

        return merchantUserRepository.save(MerchantUser.of(
            user,
            merchantUser.document(),
            merchantUser.fantasyName(),
            merchantUser.socialReason()
        ));
    }
}
