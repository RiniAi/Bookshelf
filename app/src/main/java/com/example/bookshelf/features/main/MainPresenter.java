package com.example.bookshelf.features.main;

import com.example.bookshelf.App;
import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    @Inject
    BookStorage storage;

    @Inject
    Navigator navigator;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        App.getAppComponent().injectMainPresenter(this);
        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = storage.getAll();
        if (books.isEmpty()) {
            view.hideList();
        } else {
            view.showList(books);
        }
    }

    @Override
    public void openBook(Book book) {
        navigator.openBook(book);
    }

    @Override
    public void editBook(Book book) {
        navigator.editBook(book);
    }

    @Override
    public void openBookChallenge() {
        navigator.openBookChallenge();
    }

    @Override
    public void openSearch() {
        navigator.openSearch();
    }
}
