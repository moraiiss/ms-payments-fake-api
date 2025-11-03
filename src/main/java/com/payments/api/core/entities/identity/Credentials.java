package com.payments.api.core.entities.identity;

import com.payments.api.core.vo.Email;
import com.payments.api.core.vo.Password;

import static java.util.Objects.requireNonNull;

public class Credentials {

    private final Email email;

    private final Password password;

    private Credentials(final Email email, final Password password) {

        requireNonNull(email, "Email can't be null");
        requireNonNull(password, "Password can't be null");

        this.email = email;
        this.password = password;
    }

    public static Credentials of(final String email, final String password) {
        return new Credentials(Email.of(email), Password.of(password));
    }

    public String getEmail() {
        return email.getAddress();
    }

    public String getPassword() {
        return password.getKey();
    }
}
