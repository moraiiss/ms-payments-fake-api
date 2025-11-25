package com.payments.api.core.domain.identity;

import com.payments.api.core.domain.vo.CPF;

import java.math.BigDecimal;
import java.util.Objects;

public record CommonUser(
    Long id,
    User user,
    CPF document,
    String name
) {
    public CommonUser {
        Objects.requireNonNull(user);
        Objects.requireNonNull(document);
        Objects.requireNonNull(name);
    }

    public static CommonUser of(final String name, final CPF document, final User user) {
        return new CommonUser(null, user, document, name);
    }

    public String email() {
        return this.user.getEmailAddress();
    }

    public BigDecimal balance() {
        return this.user.getCurrentBalance();
    }

    public String cpf() {
        return this.document.number();
    }
}
