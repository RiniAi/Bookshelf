package com.example.bookshelf.features.bookssearch;

import android.content.Context;
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
        SearchCall.responseListener responseListener = new SearchCall.responseListener() {
            @Override
            public void onSuccess(List<Book> books) {
                view.successfulRequest(books);

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", t.toString());
                view.errorRequest();
            }
        };

        repository = new BookRepository();
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
