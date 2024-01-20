package com.example.taskmanager.createTask.json;

import com.example.taskmanager.application.configuration.DateConfiguration;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator generator, SerializerProvider serializers)
            throws IOException {
        generator.writeString(localDateTime.format(DateConfiguration.DATETIME_FORMATTER));
    }
}

