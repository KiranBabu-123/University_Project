package com.example.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // Allow all GET requests
                .requestMatchers(new AntPathRequestMatcher("/**", "GET")).permitAll()

                // Allow POST and PUT if needed
                .requestMatchers(new AntPathRequestMatcher("/**", "POST")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/**", "PUT")).permitAll()

                // BLOCK ALL DELETE requests
                .requestMatchers(new AntPathRequestMatcher("/**", "DELETE")).denyAll()

                // Block anything else
                .anyRequest().denyAll()
            )
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable());

        return http.build();
    }
}
