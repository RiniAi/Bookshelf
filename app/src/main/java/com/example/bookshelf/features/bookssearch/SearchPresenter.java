package com.example.bookshelf.features.bookssearch;

import android.content.Context;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.Repository;
import com.example.bookshelf.database.Book;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {
    private static SearchContract.View view;
    private Repository repository;
    private Navigator navigator;

    public SearchPresenter(SearchContract.View view, Context context) {
        this.view = view;
        this.navigator = new Navigator(context);
    }

    @Override
    public void searchBook(String query) {
        repository = new Repository();
        repository.requestBooksFromApi(query);
    }

    public static void unSuccessfulRequest() {
        view.unSuccessfulRequest();
    }

    public static void successfulRequest(List<Book> books) {
        view.successfulRequest(books);
    }

    public static void errorRequest() {
        view.errorRequest();
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
    public void openMain() {
        navigator.openMain();
    }

    @Override
    public void openBookChallenge() {
        navigator.openBookChallenge();
    }

    @Override
    public void onStart() {
    }
}
