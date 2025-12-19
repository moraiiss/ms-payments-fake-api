package com.payments.api.adapters.output.feign;

import com.payments.api.adapters.output.feign.client.AuthorizationFeignClient;
import com.payments.api.adapters.output.feign.client.AuthorizationResponseDto;
import com.payments.api.core.ports.output.AuthorizationClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationClient implements AuthorizationClientPort {

    private final AuthorizationFeignClient authorizationFeignClient;

    @Override
    public AuthorizationResponseDto authorize() {
        return authorizationFeignClient.authorize();
    }

}
