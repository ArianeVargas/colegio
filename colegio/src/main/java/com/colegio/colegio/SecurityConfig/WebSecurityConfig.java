package com.colegio.colegio.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
     public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
        .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // Permitir acceso a todas las solicitudes
        )
        .formLogin(form -> form.disable()) // Deshabilitar el formulario de inicio de sesión
        .httpBasic(httpBasic -> httpBasic.disable()); 

        return http.build();
    }
}
