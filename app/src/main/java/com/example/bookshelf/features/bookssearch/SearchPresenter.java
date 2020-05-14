package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.features.bookabout.AboutBookFragment;
import com.example.bookshelf.usecases.RequestBooksUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
    public void searchBook(String query) {
        if (query.isEmpty()) {
            view.showErrorMessage();
        } else {
            runQuery(query);
        }
    }

    private void runQuery(String query) {
        RequestBooksUseCase.Params searchParams = new RequestBooksUseCase.Params(query);
        Disposable subscription = requestBooksUseCase.run(searchParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        books -> {
                            showListOrEmptyView(books);
                        },
                        // onError
                        throwable -> {
                            view.showError();
                        }
                );
        disposables.add(subscription);
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
        view.openBook(AboutBookFragment.newInstance(book));
    }

    @Override
    public void editBook(Book book) {
        navigator.editBook(book);
    }

    @Override
    public void unsubscribe() {
        disposables.clear();
    }
}
