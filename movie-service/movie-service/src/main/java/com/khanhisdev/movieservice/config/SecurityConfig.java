package com.khanhisdev.movieservice.config;


import com.khanhisdev.movieservice.exception.AuthException.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz ->
                        authz.
                                requestMatchers("/actuator/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/movie/**").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/theater/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/movie/**").hasAnyAuthority("SCOPE_USER")
                                .requestMatchers(HttpMethod.PUT, "/movie/**").permitAll()


                        .anyRequest()
                        .authenticated())
                .exceptionHandling(exception-> exception.accessDeniedHandler(new CustomAccessDeniedHandler()))
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()));
        return http.build();
    }

}
