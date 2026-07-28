package com.example.absheronikbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Отключаем защиту CSRF, чтобы принимать JSON запросы
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Разрешаем все запросы к API без логина и пароля
                );

        return http.build();
    }
}