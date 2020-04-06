package com.example.bookshelf.features.main;

import com.example.bookshelf.App;
import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.usecases.LoadBookUseCase;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    LoadBookUseCase loadUseCase;
    @Inject
    Navigator navigator;

    public MainPresenter() {
        App.getAppComponent().presenterComponent().inject(this);
    }

    @Override
    public void onStart() {
        loadBooks();
    }

    private void loadBooks() {
        List<Book> books = loadUseCase.run();
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
