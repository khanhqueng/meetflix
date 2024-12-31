package com.khanhisdev.gatewayservice.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.khanhisdev.gatewayservice.exception.AccessDeniedExceptionHandler;
import com.khanhisdev.gatewayservice.exception.AuthenticationExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    private final ObjectMapper objectMapper;
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withIssuerLocation(issuerUri).build();
    }
    @Bean
    public AuthenticationExceptionHandler authenticationExceptionHandler() {
        return new AuthenticationExceptionHandler(objectMapper);
    }

    @Bean
    public AccessDeniedExceptionHandler accessDeniedExceptionHandler() {
        return new AccessDeniedExceptionHandler(objectMapper);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/actuator/**").permitAll()
                        // get access token
                        .pathMatchers(HttpMethod.POST, "api/oauth2/v1/token").permitAll()

                        // public information
                        .pathMatchers(HttpMethod.GET, "api/v1/movie/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/v1/theater/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/v1/projectionRoom/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/v1/showtime/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/v1/category/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "api/v1/user/**").permitAll()
                        .pathMatchers(HttpMethod.GET, "api/v1/comment/movie/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "api/v1/seat/**").permitAll()

                        // api for user
                        // movie
                        .pathMatchers(HttpMethod.PUT, "api/v1/movie/**").hasAuthority("SCOPE_USER")
                        // user
                        .pathMatchers(HttpMethod.PUT, "api/v1/user/**").hasAnyAuthority("SCOPE_USER","SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.GET, "api/v1/user/**").hasAnyAuthority("SCOPE_USER","SCOPE_ADMIN")
                        // comment
                        .pathMatchers(HttpMethod.GET, "api/v1/comment/user/**").hasAuthority("SCOPE_USER")
                        .pathMatchers(HttpMethod.POST, "api/v1/comment/**").hasAuthority("SCOPE_USER")
                        .pathMatchers(HttpMethod.PUT, "api/v1/comment/**").hasAuthority("SCOPE_USER")
                        .pathMatchers(HttpMethod.DELETE, "api/v1/comment/**").hasAuthority("SCOPE_USER")
                        // order
                        .pathMatchers(HttpMethod.POST, "api/v1/order/**").hasAuthority("SCOPE_USER")
                        .pathMatchers(HttpMethod.GET, "api/v1/order/**").hasAuthority("SCOPE_USER")
                        .pathMatchers(HttpMethod.DELETE, "api/v1/order/**").hasAuthority("SCOPE_USER")

                        // api for admin
                        .pathMatchers(HttpMethod.DELETE, "api/v1/user/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.GET, "api/v1/user/all").hasAuthority("SCOPE_ADMIN")
                        // movie
                        .pathMatchers(HttpMethod.POST, "api/v1/movie/**").hasAuthority("SCOPE_ADMIN")
                        // theater
                        .pathMatchers(HttpMethod.POST, "api/v1/theater/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.PUT, "api/v1/theater/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "api/v1/theater/**").hasAuthority("SCOPE_ADMIN")
                        // projection room
                        .pathMatchers(HttpMethod.POST, "api/v1/projectionRoom/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.PUT, "api/v1/projectionRoom/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "api/v1/projectionRoom/**").hasAuthority("SCOPE_ADMIN")
                        // showtime
                        .pathMatchers(HttpMethod.POST, "api/v1/showtime/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.PUT, "api/v1/showtime/**").hasAuthority("SCOPE_ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "api/v1/showtime/**").hasAuthority("SCOPE_ADMIN")
                        // category
                        .pathMatchers(HttpMethod.POST, "api/v1/category/**").hasAuthority("SCOPE_ADMIN")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(authenticationExceptionHandler())
                        .accessDeniedHandler(accessDeniedExceptionHandler())
                        .jwt(Customizer.withDefaults())
                )
                .build();
    }

}
