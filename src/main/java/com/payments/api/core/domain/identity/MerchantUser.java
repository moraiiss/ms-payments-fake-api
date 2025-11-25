package com.payments.api.core.domain.identity;

import com.payments.api.core.domain.vo.CNPJ;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public record MerchantUser(
    Long id,
    User user,
    CNPJ document,
    String fantasyName,
    String socialReason
) {

    public MerchantUser {
        requireNonNull(user);
        requireNonNull(document);
        requireNonNull(socialReason);
    }

    public static MerchantUser of(final User user, final CNPJ document, final String fantasyName,
                                  final String socialReason) {
        return new MerchantUser(null, user, document, fantasyName, socialReason);
    }

    public String getDocumentNumber() {
        return this.document.getNumber();
    }

    public String email() {
        return this.user.getEmailAddress();
    }

    public BigDecimal balance() {
        return this.user.getCurrentBalance();
    }
}
