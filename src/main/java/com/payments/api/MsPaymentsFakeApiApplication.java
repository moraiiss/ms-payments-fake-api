package com.payments.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPaymentsFakeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPaymentsFakeApiApplication.class, args);
    }

}
