package com.trucking.Security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/v1/**")
                .allowedOrigins("http://localhost:3000","https://trucking-jebius.koyeb.app/**")
                .allowedMethods("GET","POST","PUT", "DELETE")
                .allowedHeaders(String.valueOf(Arrays.asList(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION, HttpHeaders.ACCEPT)))
                .exposedHeaders("Access-Control-Allow-Origin: https://trucking-jebius.koyeb.app/", "Vary: Origin")
                .allowCredentials(true);
    }
}