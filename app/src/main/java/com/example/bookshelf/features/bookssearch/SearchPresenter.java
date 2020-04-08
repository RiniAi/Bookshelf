package com.example.bookshelf.features.bookssearch;

import android.util.Log;

import com.example.bookshelf.App;
import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.RequestBooksUseCase;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    @Inject
    RequestBooksUseCase useCase;
    @Inject
    Navigator navigator;

    public SearchPresenter() {
        App.getAppComponent().presenterComponent().inject(this);
    }

    public SearchPresenter(RequestBooksUseCase useCase, Navigator navigator, SearchContract.View view) {
        this.useCase = useCase;
        this.navigator = navigator;
        this.view = view;
    }

    @Override
    public void searchBook(String query) {
        if (query.isEmpty()) {
            view.showErrorMessage();
        } else {
            SearchCall.ResponseListener responseListener = new SearchCall.ResponseListener() {
                @Override
                public void onSuccess(List<Book> books) {
                    if (books.isEmpty()) {
                        view.showEmptyView();
                    } else {
                        view.showBooks(books);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("error", t.toString());
                    view.showError();
                }
            };
            useCase.run(query, responseListener);
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
