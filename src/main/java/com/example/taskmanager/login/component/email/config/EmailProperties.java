package com.example.taskmanager.login.component.email.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class EmailProperties {

    private String host;
    private Integer port;
    private String protocol;
    private String auth;
    private String tls;
    private String debug;
    private String from;
    private String password;
    private String ssl;

}
