package com.dogfood.demo.springsecurity.example.security.model;

import lombok.Data;

/**
 * @author zhongfupeng
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
