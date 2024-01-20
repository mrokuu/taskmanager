package com.example.taskmanager.createTask.json;

import com.example.taskmanager.application.configuration.DateConfiguration;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        return LocalDateTime.parse(parser.getValueAsString(), DateConfiguration.DATETIME_FORMATTER);
    }
}
