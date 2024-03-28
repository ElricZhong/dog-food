package com.dogfood.authcenter.auth.security.authentication.admin.jwt;

import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhongfupeng
 */
public class AdminJwtConfigure extends AbstractHttpConfigurer<AdminJwtConfigure, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        super.init(http);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);

        http
            .authenticationProvider(applicationContext.getBean(AdminTokenProvider.class))
            .addFilterBefore(new AdminTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public static AdminJwtConfigure adminJwtLogin() {
        return new AdminJwtConfigure();
    }
}
