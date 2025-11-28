package com.payments.api.core.domain.entities;

import com.payments.api.core.domain.vo.Document;
import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;

public record User(
    Long id,
    String name,
    Document document,
    Email email,
    Password password,
    UserType userType,
    Wallet wallet
) {
    public String getEmailAddress() {
        return email.address();
    }

    public String getDocumentNumber() {
        return document.getNumber();
    }
}
