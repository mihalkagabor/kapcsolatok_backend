package org.mihalka.kapcsolatok_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {


    // CORS (Cross-Origin Resource Sharing) szabályok beállítása
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // minden végpont engedélyezése
                        .allowedOrigins("http://localhost:4200") // frontend (Angular) origin-je
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // engedélyezett HTTP metódusok
                        .allowCredentials(true); // cookie-k és authentikáció engedélyezése
            }
        };
    }

}
