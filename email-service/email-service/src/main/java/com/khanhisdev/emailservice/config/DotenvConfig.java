package com.khanhisdev.emailservice.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    public DotenvConfig() {
        Dotenv dotenv = Dotenv.configure().directory("/email-service/email-service").load();
        System.setProperty("email", dotenv.get("email"));
        System.setProperty("password", dotenv.get("password"));
    }
}
