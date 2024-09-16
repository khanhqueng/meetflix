package com.khanhisdev.movieservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.khanhisdev.movieservice.exception.AuthException.AccessDeniedExceptionHandler;
import com.khanhisdev.movieservice.exception.AuthException.AuthenticationExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz ->
                        authz.
                                requestMatchers("/actuator/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/movie/**").hasAuthority("SCOPE_USER")
                                .requestMatchers(HttpMethod.GET, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/theater/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/movie/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/movie/**").permitAll()


                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()));
        return http.build();
    }

}
