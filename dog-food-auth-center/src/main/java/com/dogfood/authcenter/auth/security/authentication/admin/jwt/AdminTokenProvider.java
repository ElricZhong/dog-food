package com.dogfood.authcenter.auth.security.authentication.admin.jwt;

import com.dogfood.authcenter.user.User;
import com.dogfood.authcenter.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author zhongfupeng
 */
@Component
@AllArgsConstructor
public class AdminTokenProvider implements AuthenticationProvider {

    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AdminJwtToken jwtToken = (AdminJwtToken)authentication;
        User user = userService.parse(jwtToken.getToken());
        if (user == null) {
            throw new BadCredentialsException("无效token");
        }

        return new UsernamePasswordAuthenticationToken(user, null, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AdminJwtToken.class.isAssignableFrom(authentication);
    }
}
