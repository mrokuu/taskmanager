package com.example.taskmanager.createTask.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;

public class TaskPreviewResponseDTO {

    private final Long id;
    private final Long fakeId;
    private final String name;
    private final Instant dateTimeFrom;
    private final Instant dateTimeTo;

    public TaskPreviewResponseDTO(Long id, Long fakeId, String name, Instant dateTimeFrom, Instant dateTimeTo) {
        this.id = id;
        this.fakeId = fakeId;
        this.name = name;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    public Long getId() {
        return id;
    }

    public Long getFakeId() {
        return fakeId;
    }

    public String getName() {
        return name;
    }

    public Instant getDateTimeFrom() {
        return dateTimeFrom;
    }

    public Instant getDateTimeTo() {
        return dateTimeTo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name)
                .append("dateTimeFrom", dateTimeFrom).append("dateTimeTo", dateTimeTo).toString();
    }
}
