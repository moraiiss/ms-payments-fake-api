package com.payments.api.core.ports.output;

import com.payments.api.core.domain.entities.payment.Wallet;

public interface WalletRepositoryPort {

    void save(Wallet wallet);

}
