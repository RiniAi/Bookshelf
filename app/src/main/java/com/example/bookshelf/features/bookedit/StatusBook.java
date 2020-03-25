package com.example.bookshelf.features.bookedit;

public class StatusBook {
    private String name;
    private Object id;

    public StatusBook(String name, Object id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
