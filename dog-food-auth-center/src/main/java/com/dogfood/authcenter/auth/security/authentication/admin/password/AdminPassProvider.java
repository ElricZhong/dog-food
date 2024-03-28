package com.dogfood.authcenter.auth.security.authentication.admin.password;

import java.util.UUID;

import com.dogfood.authcenter.auth.security.model.LoginSuccessToken;
import com.dogfood.authcenter.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author zhongfupeng
 */
@Component
@AllArgsConstructor
public class AdminPassProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminPasswordToken token = (AdminPasswordToken) authentication;

        if ("root".equals(token.getUsername()) && "root".equals(token.getPassword())) {
            LoginSuccessToken loginSuccessToken = new LoginSuccessToken(UUID.randomUUID().toString(), "root");
            userService.add(loginSuccessToken.getToken(), loginSuccessToken.getUsername());
            return loginSuccessToken;
        } else {
            throw new UsernameNotFoundException("找不到用户");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AdminPasswordToken.class.isAssignableFrom(authentication);
    }
}
