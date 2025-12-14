package com.payments.api.core.domain.entities;

import com.payments.api.core.domain.vo.CPF;

import java.math.BigDecimal;

public record Consumer(
    Long id,
    String name,
    CPF document,
    User user,
    Wallet wallet
) implements Payer, Payee {
    public static Consumer of(final Long id, final String name, final String document, final String email,
                              final String password, final Wallet wallet) {
        return new Consumer(id, name, CPF.of(document), User.of(email, password), wallet);
    }

    public static Consumer of(final String name, final String document, final String email) {
        return new Consumer(null, name, CPF.of(document), User.of(email), Wallet.of());
    }

    public String getEmailAddress() {
        return user.getEmail();
    }

    public String getDocumentNumber() {
        return document.getNumber();
    }

    public String getPasswordKey() {
        return user.getPassword();
    }

    @Override
    public Wallet credit(final BigDecimal amount) {
        return wallet.credit(amount);
    }

    @Override
    public Wallet debit(final BigDecimal amount) {
        return wallet.debit(amount);
    }
}
