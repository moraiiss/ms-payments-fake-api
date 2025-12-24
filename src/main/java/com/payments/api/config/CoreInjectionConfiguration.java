package com.payments.api.config;

import com.payments.api.core.ports.input.UserValidatorServicePort;
import com.payments.api.core.ports.output.AuthorizationClientPort;
import com.payments.api.core.ports.output.ConsumerRepositoryPort;
import com.payments.api.core.ports.output.SellerRepositoryPort;
import com.payments.api.core.ports.output.WalletRepositoryPort;
import com.payments.api.core.service.UserValidatorService;
import com.payments.api.core.usecase.ConsumerCreatorUseCase;
import com.payments.api.core.usecase.SellerCreatorUseCase;
import com.payments.api.core.usecase.TransactionCreatorUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjectionConfiguration {

    @Bean
    public UserValidatorService userValidatorService(final SellerRepositoryPort sellerRepositoryPort,
                                                     final ConsumerRepositoryPort consumerRepositoryPort) {
        return new UserValidatorService(sellerRepositoryPort, consumerRepositoryPort);
    }

    @Bean
    public ConsumerCreatorUseCase consumerCreatorUseCase(final ConsumerRepositoryPort consumerRepositoryPort,
                                                         final UserValidatorServicePort userValidatorServicePort) {
        return new ConsumerCreatorUseCase(consumerRepositoryPort, userValidatorServicePort);
    }

    @Bean
    public SellerCreatorUseCase sellerCreatorUseCase(final SellerRepositoryPort sellerRepositoryPort,
                                                       final UserValidatorServicePort userValidatorServicePort) {
        return new SellerCreatorUseCase(sellerRepositoryPort, userValidatorServicePort);
    }

    @Bean
    public TransactionCreatorUseCase transactionCreatorUseCase(final ConsumerRepositoryPort consumerRepositoryPort,
                                                               final SellerRepositoryPort sellerRepositoryPort,
                                                               final WalletRepositoryPort walletRepositoryPort,
                                                               final AuthorizationClientPort authorizationClientPort) {
        return new TransactionCreatorUseCase(consumerRepositoryPort, sellerRepositoryPort, walletRepositoryPort,
            authorizationClientPort);
    }

}
