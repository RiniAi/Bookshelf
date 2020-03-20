package com.example.bookshelf.features.bookssearch;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;
    private BookRepository repository;
    private Navigator navigator;

    public SearchPresenter(SearchContract.View view, Context context) {
        this.view = view;
        this.navigator = new Navigator(context);
    }

    @Override
    public void searchBook(String query) {
        repository = new BookRepository();
        repository.requestBooksFromApi(query);

        repository.onSuccessfulCall(new SearchCall.onSuccessfulCall() {
            @Override
            public void getBooks(List<Book> books) {
                view.successfulRequest(books);
            }
        });

        repository.onUnSuccessfulCall(new SearchCall.onUnSuccessfulCall() {
            @Override
            public void getThrowable(Throwable t) {
                Log.e("error", t.toString());
                view.errorRequest();
            }
        });
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

    @Override
    public void onStartWitchData(Bundle bundle) {

    }
}
