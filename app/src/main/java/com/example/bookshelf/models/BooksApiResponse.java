package com.example.bookshelf.models;

import java.util.ArrayList;
import java.util.List;

public class BooksApiResponse {
    private List<BooksApiResponseItem> items = new ArrayList<BooksApiResponseItem>();

    public List<BooksApiResponseItem> getItems() {
        return items;
    }

    public void setItems(List<BooksApiResponseItem> items) {
        this.items = items;
    }
}
