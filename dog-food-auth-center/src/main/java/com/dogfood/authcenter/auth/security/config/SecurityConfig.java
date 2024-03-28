package com.dogfood.authcenter.auth.security.config;

import com.dogfood.authcenter.auth.security.entrypoint.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static com.dogfood.authcenter.auth.security.authentication.admin.jwt.AdminJwtConfigure.adminJwtLogin;
import static com.dogfood.authcenter.auth.security.authentication.admin.password.AdminPassConfigure.adminPassLogin;

/**
 * @author zhongfupeng
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain adminLoginChain(HttpSecurity http) throws Exception {
        http.apply(adminPassLogin());
        http.apply(adminJwtLogin());

        http
            .antMatcher("/admin/**")
            .authorizeRequests(authorize -> authorize
                .antMatchers("/admin/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(adminEntryPoint())
            )
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public CustomAuthenticationEntryPoint adminEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
