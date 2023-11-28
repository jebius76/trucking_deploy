package com.trucking.Security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/v1/**")
                .allowedOrigins("http://localhost:3000","http://200.45.208.45:9896/api/v1/**")
                .allowedMethods("GET","POST","PUT", "DELETE")
                .exposedHeaders("CONTENT_TYPE", "AUTHORIZATION","ACCEPT")
                .allowCredentials(true);
    }
}
