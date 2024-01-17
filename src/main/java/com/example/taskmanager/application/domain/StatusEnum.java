package com.example.taskmanager.application.domain;

public enum StatusEnum {

    APPA_STATUS_1("Appa Status 1"), APPA_STATUS_2("Appa Status 2"), APPA_STATUS_3("Appa Status 3"),
    APPA_STATUS_4("Appa Status 4");

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
