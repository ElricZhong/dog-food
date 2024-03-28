package com.dogfood.authcenter.auth.security.authentication.admin.password;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhongfupeng
 */
@AllArgsConstructor
@Getter
public class AdminPasswordToken implements Authentication {

    private final String username;
    private final String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
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
