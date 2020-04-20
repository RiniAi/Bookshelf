package com.example.bookshelf.features.bookssearch;

import android.util.Log;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.RequestBooksUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    @Inject
    RequestBooksUseCase requestBooksUseCase;
    @Inject
    Navigator navigator;

    private CompositeDisposable disposables;

    @Inject
    public SearchPresenter(RequestBooksUseCase requestBooksUseCase, Navigator navigator) {
        this.requestBooksUseCase = requestBooksUseCase;
        this.navigator = navigator;
        this.disposables = new CompositeDisposable();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }

    @Override
    public void searchBook(String query) {
        if (query.isEmpty()) {
            view.showErrorMessage();
        } else {
            runQuery(query);
        }
    }

    private void runQuery(String query) {
        SearchCall.ResponseListener responseListener = new SearchCall.ResponseListener() {
            @Override
            public void onSuccess(List<Book> books) {
                showListOrEmptyView(books);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("error", t.toString());
                view.showError();
            }
        };
        requestBooksUseCase.run(query, responseListener);
    }

    private void showListOrEmptyView(List<Book> books) {
        if (books.isEmpty()) {
            view.showEmptyView();
        } else {
            view.showBooks(books);
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
    public void openMain() {
        navigator.openMain();
    }

    @Override
    public void openBookChallenge() {
        navigator.openBookChallenge();
    }
}
