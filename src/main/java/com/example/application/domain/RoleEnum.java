package com.example.application.domain;

public enum RoleEnum {

    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
