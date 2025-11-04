package com.payments.api.core.entities.identity;

import com.payments.api.core.entities.payments.Payee;
import com.payments.api.core.entities.payments.Payer;
import com.payments.api.core.entities.payments.Wallet;
import com.payments.api.core.vo.CPF;

import static java.util.Objects.requireNonNull;

public class Consumer implements Payer, Payee {

    private final Long id;

    private final String name;

    private final CPF document;

    private final Credentials credentials;

    private final Wallet wallet;

    private Consumer(final Long id, final String name, final CPF document, final Credentials credentials,
                     final Wallet wallet) {
        requireNonNull(name, "Name is required!");
        requireNonNull(document, "Document is required!");
        requireNonNull(credentials, "Auth user data can't be null");
        requireNonNull(wallet, "Wallet data can't be null");

        this.id = id;
        this.name = name;
        this.document = document;
        this.credentials = credentials;
        this.wallet = wallet;
    }

    public static Consumer of(final Long id, final String name, final String document, final String emailAddress,
                              final String passwordKey) {
        return new Consumer(id, name, CPF.of(document), Credentials.of(emailAddress, passwordKey), Wallet.of());
    }

    @Override
    public void credit(double value) {
        this.wallet.credit(value);
    }

    @Override
    public void debit(double value) {
        this.wallet.debit(value);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDocument() {
        return document.getNumber();
    }

    public String getEmail() {
        return credentials.getEmail();
    }

    public String getPassword() { return credentials.getPassword(); }
}
