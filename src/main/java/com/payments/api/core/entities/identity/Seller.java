package com.payments.api.core.entities.identity;

import com.payments.api.core.entities.payments.Payee;
import com.payments.api.core.entities.payments.Wallet;
import com.payments.api.core.vo.CNPJ;

import static java.util.Objects.requireNonNull;

public class Seller implements Payee {

    private final String socialReason;

    private final String fantasyName;

    private final CNPJ document;

    private final Credentials credentials;

    private final Wallet wallet;

    private Seller(final String socialReason, final String fantasyName, final CNPJ document,
                   final Credentials credentials, final Wallet wallet) {
        requireNonNull(socialReason, "Social Reason is required");
        requireNonNull(document, "Document is required");
        requireNonNull(credentials, "Auth user data can't be null");
        requireNonNull(wallet, "Wallet data can't be null");

        this.socialReason = socialReason;
        this.fantasyName = fantasyName;
        this.document = document;
        this.credentials = credentials;
        this.wallet = wallet;
    }

    public static Seller of(final String socialReason, final String fantasyName, final String document, final String emailAddress,
                              final String passwordKey) {
        return new Seller(socialReason, fantasyName, CNPJ.of(document), Credentials.of(emailAddress, passwordKey),
            Wallet.of());
    }

    @Override
    public void credit(double value) {
        this.wallet.credit(value);
    }

    public String getSocialReason() {
        return this.socialReason;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public String getDocument() {
        return document.getNumber();
    }

    public String getEmail() {
        return credentials.getEmail();
    }

    public String getPassword() { return credentials.getPassword(); }
}
