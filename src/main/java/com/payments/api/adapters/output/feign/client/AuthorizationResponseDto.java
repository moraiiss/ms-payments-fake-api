package com.payments.api.adapters.output.feign.client;

public record AuthorizationResponseDto(
    boolean authorized
) {
}
