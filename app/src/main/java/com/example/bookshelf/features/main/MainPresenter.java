package com.example.bookshelf.features.main;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private Navigator navigator;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void initNavigator() {
        navigator = new Navigator(view);
    }

    @Override
    public void loadBooks() {
        BookStorage storage = new BookStorage();
        List<Book> booksFromDatabase = storage.getBooks();
        if (booksFromDatabase.isEmpty()) {
            view.hideList();
        } else {
            view.showList();
            view.loadBooks(booksFromDatabase);
        }
    }

    @Override
    public void openBook(Book book) {
        initNavigator();
        navigator.openBook(book);
    }

    @Override
    public void editBook(Book book) {
        initNavigator();
        navigator.editBook(book);
    }

    @Override
    public void openBookChallenge() {
        initNavigator();
        navigator.openBookChallenge();
    }

    @Override
    public void openSearchActivity() {
        initNavigator();
        navigator.openSearchActivity();
    }
}
