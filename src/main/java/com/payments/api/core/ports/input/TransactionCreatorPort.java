package com.payments.api.core.ports.input;

import com.payments.api.core.domain.entities.payment.Transaction;

public interface TransactionCreatorPort {

    Transaction create(final Transaction transaction);

}
