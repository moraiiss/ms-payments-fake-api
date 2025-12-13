package com.payments.api.core.domain.entities;

import com.payments.api.core.domain.vo.*;

public record Consumer(
    Long id,
    String name,
    Document document,
    Email email, // user
    Password password, // user
    Wallet wallet
) {
    public static Consumer of(final Long id, final String name, final String document, final String email,
                              final String password, final Wallet wallet) {
        return new Consumer(id, name, CPF.of(document), Email.of(email), Password.of(password), wallet);
    }

    public static Consumer of(final String name, final String document, final String email) {
        return new Consumer(null, name, CPF.of(document), Email.of(email), Password.of(), Wallet.of());
    }

    public String getEmailAddress() {
        return email.address();
    }

    public String getDocumentNumber() {
        return document.getNumber();
    }
}
