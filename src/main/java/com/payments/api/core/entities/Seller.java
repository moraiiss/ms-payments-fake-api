package com.payments.api.core.entities;

import com.payments.api.core.entities.contracts.Payee;
import com.payments.api.core.vo.CPF;

import static java.util.Objects.requireNonNull;

public class Seller implements Payee {

    private UserIdentity userIdentity;
    private Credentials credentials;
    private Wallet wallet;

    private Seller(final UserIdentity userIdentity, final Credentials credentials, final Wallet wallet) {
        requireNonNull(userIdentity, "User identity data can't be null");
        requireNonNull(credentials, "Auth user data can't be null");
        requireNonNull(wallet, "Wallet data can't be null");

        this.userIdentity = userIdentity;
        this.credentials = credentials;
        this.wallet = wallet;
    }

    public static Seller of(final String name, final String document, final String emailAddress,
                              final String passwordKey) {
        return new Seller(
            UserIdentity.of(name, CPF.of(document)),
            Credentials.of(emailAddress, passwordKey),
            Wallet.of()
        );
    }

    @Override
    public void credit(double value) {
        this.wallet.credit(value);
    }

    public String getName() {
        return this.userIdentity.getName();
    }

    public String getDocument() {
        return userIdentity.getDocument();
    }

    public String getEmail() {
        return credentials.getEmail();
    }

    public String getPassword() { return credentials.getPassword(); }
}
