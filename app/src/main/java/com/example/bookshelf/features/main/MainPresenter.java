package com.example.bookshelf.features.main;

import com.example.bookshelf.App;
import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    BookStorage storage;
    @Inject
    Navigator navigator;

    @Override
    public void onStart() {
        App.getAppComponent().mainPresenterComponent().inject(this);
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
