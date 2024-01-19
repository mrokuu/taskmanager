package com.example.taskmanager.task.component.database;

public final class DTColumn {

    private String data;
    private String title;
    private String name;
    private String sClass;
    private boolean searchable;
    private boolean orderable;
    private DTSearchValue search;

    public DTColumn() {
    }

    public DTColumn(String data, String title) {
        this.data = data;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isOrderable() {
        return orderable;
    }

    public void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    public DTSearchValue getSearch() {
        return search;
    }

    public void setSearch(DTSearchValue search) {
        this.search = search;
    }
}
