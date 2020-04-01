package com.example.bookshelf.features.bookchallenge;

import android.content.SharedPreferences;

import com.example.bookshelf.App;
import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.LocalBookStorage;

import java.util.List;

import javax.inject.Inject;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;

public class BookChallengePresenter extends BasePresenter<BookChallengeContract.View> implements BookChallengeContract.Presenter {
    private static final String STORAGE_COUNTER = "counter";
    @Inject
    LocalBookStorage storage;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Navigator navigator;

    public BookChallengePresenter() {
        App.getAppComponent().presenterComponent().inject(this);
    }

    @Override
    public void onStart() {
        loadCounter();
        loadBooks();
    }

    private void loadCounter() {
        String counter = sharedPreferences.getString(STORAGE_COUNTER, "0");
        view.changeCounter(counter);
        changeCounterForBar(counter);
    }

    private void changeCounterForBar(String counter) {
        view.changeCounterForBar(Integer.parseInt(counter));
    }

    private void loadBooks() {
        List<Book> books = storage.getAllWithStatus(FINISH_READING);
        view.showList(books);
        changeProgress(books);
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
