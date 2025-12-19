package com.payments.api.core.domain.entities.identity;

import com.payments.api.core.domain.entities.payment.Wallet;
import com.payments.api.core.domain.vo.CNPJ;

import java.math.BigDecimal;
import java.util.Objects;

public record Seller(Long id, String socialReason, String fantasyName, CNPJ document, User user, Wallet wallet)
    implements Payee {

    public Seller {
        if (socialReason == null || socialReason.isBlank()) {
            Objects.requireNonNull(socialReason, "Field social reason is required");
        }
    }

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

    @Override
    public Wallet credit(final BigDecimal amount) {
        return wallet.credit(amount);
    }
}
