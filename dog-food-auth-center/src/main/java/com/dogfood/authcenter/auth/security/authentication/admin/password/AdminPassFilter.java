package com.dogfood.authcenter.auth.security.authentication.admin.password;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dogfood.authcenter.auth.security.model.PasswordLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

/**
 * @author zhongfupeng
 */
public class AdminPassFilter extends AbstractAuthenticationProcessingFilter {

    public AdminPassFilter(
        AuthenticationManager authenticationManager) {
        super("/admin/auth/passwordLogin", authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        PasswordLoginRequest passwordLoginRequest = objectMapper.readValue(request.getInputStream(), PasswordLoginRequest.class);

        AdminPasswordToken token = new AdminPasswordToken(passwordLoginRequest.getUsername(), passwordLoginRequest.getPassword());
        return getAuthenticationManager().authenticate(token);
    }
}
