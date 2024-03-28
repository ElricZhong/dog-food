package com.dogfood.authcenter.auth.security.model;

import lombok.Data;

/**
 * @author zhongfupeng
 */
@Data
public class LoginSuccessResponse {
    private String token;
    private String username;
}
