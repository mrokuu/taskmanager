package com.example.taskmanager.task.dto.request;

import com.example.taskmanager.task.component.database.DTOrder;
import com.example.taskmanager.task.component.database.DTSearchValue;

public class DTSearchRequestDTO {

    private int page;
    private int length;
    private DTOrder order;
    private DTSearchValue search;

    public DTSearchRequestDTO() {
    }

    public DTSearchRequestDTO(int page, int length, DTOrder order, DTSearchValue search) {
        this.page = page;
        this.length = length;
        this.order = order;
        this.search = search;
    }

    public int getLength() {
        return this.length;
    }

    public DTOrder getOrder() {
        return this.order;
    }

    public DTSearchValue getSearch() {
        return this.search;
    }

    public int getPage() {
        return this.page;
    }

    public void setOrder(DTOrder order) {
        this.order = order;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
