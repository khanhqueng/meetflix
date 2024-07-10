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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.

                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz ->
                        authz.
                                requestMatchers(HttpMethod.GET, "/movie/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/theater/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/showtime/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/movie/**").hasAuthority("SCOPE_USER")


                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()));
        return http.build();
    }

}
