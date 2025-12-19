package com.payments.api.config;

import com.payments.api.adapters.output.db.ConsumerRepository;
import com.payments.api.adapters.output.db.SellerRepository;
import com.payments.api.core.service.UserValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjectionConfiguration {

    @Bean
    public UserValidatorService userValidatorService(final SellerRepository sellerRepository,
                                                     final ConsumerRepository consumerRepository) {
        return new UserValidatorService(sellerRepository, consumerRepository);
    }

}
