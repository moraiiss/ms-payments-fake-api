package com.payments.api.core.domain.entities;

import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;

public record User(
    Email email,
    Password password
) {
    public static User of(final String email, final String password) {
        return new User(Email.of(email), Password.of(password));
    }

    public static User of(final String email) {
        return new User(Email.of(email), Password.of());
    }

    public String getEmail() {
        return email.address();
    }

    public String getPassword() {
        return password.key();
    }
}
