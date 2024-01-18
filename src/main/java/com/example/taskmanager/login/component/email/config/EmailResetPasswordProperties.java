package com.example.taskmanager.login.component.email.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EmailResetPasswordProperties {

    private String subject;
    private String body;


}
