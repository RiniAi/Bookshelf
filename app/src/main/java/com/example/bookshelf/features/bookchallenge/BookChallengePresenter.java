package com.example.bookshelf.features.bookchallenge;

import android.content.SharedPreferences;

import com.example.bookshelf.App;
import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import java.util.List;

import javax.inject.Inject;

public class BookChallengePresenter extends BasePresenter<BookChallengeContract.View> implements BookChallengeContract.Presenter {
    private static final String STORAGE_COUNTER = "counter";
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Navigator navigator;

    public BookChallengePresenter() {
        App.getAppComponent().presenterComponent().inject(this);
    }

    public BookChallengePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, SharedPreferences sharedPreferences, Navigator navigator, BookChallengeContract.View view) {
        this.searchBookWithStatusUseCase = searchBookWithStatusUseCase;
        this.sharedPreferences = sharedPreferences;
        this.navigator = navigator;
        this.view = view;
    }

    @Override
    public void onStart() {
        loadCounter();
        loadBooks();
    }

    private void loadCounter() {
        String counter;
        try {
            counter = sharedPreferences.getString(STORAGE_COUNTER, "0");
        } catch (NullPointerException e) {
            counter = "0";
        }
        view.changeCounter(counter);
        changeCounterForBar(counter);
    }

    private void changeCounterForBar(String counter) {
        try {
            view.changeCounterForBar(Integer.parseInt(counter));
        } catch (NumberFormatException e) {
            view.changeCounterForBar(0);
        }
    }

    private void loadBooks() {
        List<Book> books = searchBookWithStatusUseCase.run();
        if (books.isEmpty()) {
            view.hideList();
        } else {
            view.showList(books);
            changeProgress(books);
        }
    }

    private void changeProgress(List<Book> books) {
        view.changeProgress(String.valueOf(books.size()));
    }

    @Override
    public void onProgressChanged(int i) {
        view.changeCounter(String.valueOf(i));
    }

    @Override
    public void saveCounter(String counter) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORAGE_COUNTER, counter);
        editor.apply();
        view.showCounterSavedMessage();
    }

    @Override
    public void openBook(Book book) {
        navigator.openBook(book);
    }

    @Override
    public void openMain() {
        navigator.openMain();
    }
}
