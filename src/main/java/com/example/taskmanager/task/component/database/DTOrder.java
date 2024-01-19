package com.example.taskmanager.task.component.database;

public class DTOrder {

    private String column;
    private String dir;

    public DTOrder() {
    }

    public DTOrder(String column, String dir) {
        this.column = column;
        this.dir = dir;
    }

    public String getColumn() {
        return this.column;
    }

    public String getDir() {
        return this.dir;
    }
}
