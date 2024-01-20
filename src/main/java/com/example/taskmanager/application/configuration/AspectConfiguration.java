package com.example.taskmanager.application.configuration;

import com.example.taskmanager.application.aspect.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfiguration {

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}

