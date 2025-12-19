package com.payments.api.adapters.output.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorization-service", url = "${client.authorization.url}")
public interface AuthorizationFeignClient {

    @GetMapping("/auth")
    AuthorizationResponseDto authorize();

}
