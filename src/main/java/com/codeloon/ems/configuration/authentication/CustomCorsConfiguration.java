package com.codeloon.ems.configuration.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


@RequiredArgsConstructor
@Component
public class CustomCorsConfiguration implements CorsConfigurationSource {

    private final CorsProperties corsProperties;

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(corsProperties.getUris());
        config.setAllowedMethods(corsProperties.getMethods());
        config.setAllowedHeaders(List.of("*"));
        return config;
    }
}
