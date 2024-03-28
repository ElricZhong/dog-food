package com.dogfood.authcenter.auth.security.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dogfood.authcenter.common.model.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author zhongfupeng
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        response.getOutputStream().write(objectMapper.writeValueAsBytes(JsonObject.fail("登录失败")));
    }
}
