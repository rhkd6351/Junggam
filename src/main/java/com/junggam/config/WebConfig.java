package com.junggam.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // /** -> 와일드 카드
                .allowedOrigins("http://localhost:8080", "http://localhost:8081")
                .allowedMethods("GET", "PUT", "POST", "DELETE");
    }
}
