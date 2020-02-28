package com.example.bookshelf.models;

import java.util.ArrayList;
import java.util.List;

public class BookItem {
    private List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
