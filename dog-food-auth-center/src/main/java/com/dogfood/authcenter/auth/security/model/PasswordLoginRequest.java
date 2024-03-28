package com.dogfood.authcenter.auth.security.model;

import lombok.Data;

/**
 * @author zhongfupeng
 */
@Data
public class PasswordLoginRequest {
    private String username;
    private String password;
}
