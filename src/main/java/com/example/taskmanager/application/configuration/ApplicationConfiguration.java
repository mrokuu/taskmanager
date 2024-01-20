package com.example.taskmanager.application.configuration;

import com.example.taskmanager.application.component.CustomWebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfiguration {

    private final CustomWebMvcConfigurer webMvcConfigurer;

    public ApplicationConfiguration(CustomWebMvcConfigurer webMvcConfigurer) {
        this.webMvcConfigurer = webMvcConfigurer;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return webMvcConfigurer;
    }

}

