package com.payments.api.core.domain.entities;

import com.payments.api.core.domain.vo.Document;
import com.payments.api.core.domain.vo.Email;
import com.payments.api.core.domain.vo.Password;

public record Seller(
    Long id,
    String socialReason,
    String fantasyName,
    Document document,
    Email email, // user
    Password password, // user
    Wallet wallet
) {
}
