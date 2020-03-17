package com.example.bookshelf.activities;

import com.example.bookshelf.Storage;
import com.example.bookshelf.room.Book;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadBooks() {
        Storage storage = new Storage();
        List<Book> booksFromDatabase = storage.searchForBooksWithStatus();
        if (booksFromDatabase.isEmpty()) {
            view.hideList();
        } else {
            view.showList();
            view.loadBooks(booksFromDatabase);
        }
    }
}
