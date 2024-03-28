package com.dogfood.authcenter.auth.security.model;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhongfupeng
 */
@Data
@AllArgsConstructor
public class LoginSuccessToken implements Authentication {
    private final String token;
    private final String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return username;
    }
}
