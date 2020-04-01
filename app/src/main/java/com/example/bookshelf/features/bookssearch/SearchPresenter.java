package com.example.bookshelf.features.bookssearch;

import android.util.Log;

import com.example.bookshelf.App;
import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    @Inject
    BookRepository repository;
    @Inject
    Navigator navigator;

    public SearchPresenter() {
        App.getAppComponent().presenterComponent().inject(this);
    }

    @Override
    public void searchBook(String query) {
        SearchCall.responseListener responseListener = new SearchCall.responseListener() {
            @Override
            public void onSuccess(List<Book> books) {
                view.showBooks(books);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", t.toString());
                view.showError();
            }
        };
        repository.requestBooksFromApi(query, responseListener);
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
}