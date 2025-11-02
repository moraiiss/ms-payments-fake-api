package com.payments.api.core.entities;

import com.payments.api.core.entities.contracts.Document;

import static java.util.Objects.requireNonNull;

public class UserIdentity {
    private String name;
    private Document document;

    private UserIdentity(final String name, final Document document) {
        requireNonNull(name, "Name can't be null");
        requireNonNull(document, "Document can't be null");

        this.name = name;
        this.document = document;
    }

    public static UserIdentity of(final String name, final Document document) {
        return new UserIdentity(name, document);
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document.getNumber();
    }
}
