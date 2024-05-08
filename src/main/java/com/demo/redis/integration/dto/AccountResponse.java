package com.demo.redis.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountResponse {
    private String id;
    private String address;
    private String phone;
}
