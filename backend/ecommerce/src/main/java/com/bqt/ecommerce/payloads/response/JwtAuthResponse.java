package com.bqt.ecommerce.payloads.response;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;

    private AccountResponse account;
}
