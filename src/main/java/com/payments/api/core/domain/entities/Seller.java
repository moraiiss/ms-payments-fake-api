package com.payments.api.core.domain.entities;

import com.payments.api.core.domain.vo.CNPJ;

public record Seller(
    Long id,
    String socialReason,
    String fantasyName,
    CNPJ document,
    User user,
    Wallet wallet
) {
    public static Seller of(final Long id, final String socialReason, final String fantasyName,
                            final String document, final String email, final String password, final Wallet wallet) {
        return new Seller(id, socialReason, fantasyName, CNPJ.of(document), User.of(email, password), wallet);
    }

    public static Seller of(final String socialReason, final String fantasyName, final String document,
                            final String email) {
        return new Seller(null, socialReason, fantasyName, CNPJ.of(document), User.of(email), Wallet.of());
    }

    public String getDocumentNumber() {
        return document.getNumber();
    }

    public String getEmailAddress() {
        return user.getEmail();
    }

    public String getPassword() {
        return user.getPassword();
    }
}
