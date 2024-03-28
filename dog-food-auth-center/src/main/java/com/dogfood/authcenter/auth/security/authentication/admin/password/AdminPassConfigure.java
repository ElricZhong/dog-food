package com.dogfood.authcenter.auth.security.authentication.admin.password;

import com.dogfood.authcenter.auth.security.handler.CustomAuthenticationFailureHandler;
import com.dogfood.authcenter.auth.security.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author zhongfupeng
 */
public class AdminPassConfigure extends AbstractHttpConfigurer<AdminPassConfigure, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        super.init(http);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);

        AdminPassFilter adminPassFilter = new AdminPassFilter(builder.getObject());

        CustomAuthenticationFailureHandler customAuthenticationFailureHandler = applicationContext.getBean(CustomAuthenticationFailureHandler.class);
        adminPassFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler = applicationContext.getBean(CustomAuthenticationSuccessHandler.class);
        adminPassFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);

        http
            .authenticationProvider(applicationContext.getBean(AdminPassProvider.class))
            .addFilterBefore(adminPassFilter, UsernamePasswordAuthenticationFilter.class);
    }

    public static AdminPassConfigure adminPassLogin() {
        return new AdminPassConfigure();
    }
}
