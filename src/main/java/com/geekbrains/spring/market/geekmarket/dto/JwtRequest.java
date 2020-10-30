package com.geekbrains.spring.market.geekmarket.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
