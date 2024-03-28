package com.dogfood.authcenter.auth.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dogfood.authcenter.auth.security.model.LoginSuccessToken;
import com.dogfood.authcenter.auth.security.model.LoginSuccessResponse;
import com.dogfood.authcenter.common.model.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author zhongfupeng
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        LoginSuccessToken token = (LoginSuccessToken)authentication;
        LoginSuccessResponse res = new LoginSuccessResponse();
        res.setToken(token.getToken());
        res.setUsername(token.getUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getOutputStream().write(objectMapper.writeValueAsBytes(JsonObject.success(res)));
    }
}
