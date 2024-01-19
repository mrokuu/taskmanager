package com.example.taskmanager.task.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DTRecordsResponseDTO {

    private final List<TaskResponseDTO> records = new ArrayList<>();
    private final long recordsTotal;
    private final int recordsFiltered;

    public DTRecordsResponseDTO(List<TaskResponseDTO> records, long recordsTotal,
                                int recordsFiltered) {
        this.records.addAll(records);
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
    }

    public List<TaskResponseDTO> getRecords() {
        return records;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this)
                .append("records", records.stream().map(column -> column.getName()).collect(Collectors.joining(", ")))
                .append("recordsTotal", recordsTotal).append("recordsFiltered", recordsFiltered).toString();
    }
}
