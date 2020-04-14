package com.example.bookshelf.features.main;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.LoadBookUseCase;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    LoadBookUseCase loadBookUseCase;
    @Inject
    Navigator navigator;

    @Inject
    public MainPresenter(LoadBookUseCase loadBookUseCase, Navigator navigator) {
        this.loadBookUseCase = loadBookUseCase;
        this.navigator = navigator;
    }

    @Override
    public void onStart() {
        List<Book> books = loadBookUseCase.run();
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
