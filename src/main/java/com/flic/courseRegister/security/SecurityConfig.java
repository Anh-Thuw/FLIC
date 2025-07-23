package com.flic.courseRegister.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Tạm thời cho phép tất cả request không cần xác thực (chỉ dùng khi dev)
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Cho phép toàn bộ request
                )
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable()); // Tắt Basic Auth

        return http.build();
    }
}
