package com.payments.api.core.domain.identity;

import com.payments.api.core.domain.payment.Wallet;
import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;
import com.payments.api.repository.jpa.entities.UserType;

import java.math.BigDecimal;
import java.util.Objects;

public record User(
    Long id,
    Wallet wallet,
    Email email,
    Password password,
    UserType userType
) {
    public User {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
    }

    public static User of(final Email email, final Password password, final UserType userType) {
        return new User(null, Wallet.of(), email, password, userType);
    }

    public BigDecimal getCurrentBalance() {
        return this.wallet.balance();
    }

    public String getEmailAddress() {
        return email.address();
    }

    public String getPasswordKey() {
        return this.password.key();
    }
}
