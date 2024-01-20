package com.example.taskmanager.application.configuration;

import java.time.format.DateTimeFormatter;

public class DateConfiguration {

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
}
