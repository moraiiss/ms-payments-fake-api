package com.payments.api.core.ports.output;

import com.payments.api.adapters.output.feign.client.AuthorizationResponseDto;

public interface AuthorizationClientPort {

    AuthorizationResponseDto authorize();

}
