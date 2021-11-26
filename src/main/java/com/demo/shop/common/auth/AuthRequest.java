package com.demo.shop.common.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String user;
    private String password;
}
