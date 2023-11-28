package com.trucking.Security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/v1/**")
                .allowedOrigins("http://localhost:3000","https://trucking-jebius.koyeb.app/")
                .allowedMethods("GET","POST","PUT", "DELETE")
                .exposedHeaders("CONTENT_TYPE", "AUTHORIZATION","ACCEPT")
                .allowCredentials(true);
    }
}