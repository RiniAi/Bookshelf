package com.example.bookshelf.features.main;

import android.content.Context;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private Navigator navigator;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.navigator = new Navigator(context);
    }

    @Override
    public void onStart() {
        loadBooks();
    }

    private void loadBooks() {
        BookStorage storage = new BookStorage();
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
