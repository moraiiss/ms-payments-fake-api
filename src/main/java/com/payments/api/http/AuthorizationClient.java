package com.payments.api.http;

import com.payments.api.http.dto.AuthorizationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorization-service", url = "${client.authorization.url}")
public interface AuthorizationClient {

    @GetMapping("/auth")
    AuthorizationResponseDto authorize();

}
