package com.example.bookshelf.features.bookchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.database.Book;
import com.example.bookshelf.database.BookStorage;

import java.util.List;

import static com.example.bookshelf.database.Book.BookStatus.FINISH_READING;

public class BookChallengePresenter implements BookChallengeContract.Presenter {
    private BookChallengeContract.View view;
    private Navigator navigator;
    private static final String STORAGE_COUNTER = "counter";
    private SharedPreferences sharedPreferences;

    public BookChallengePresenter(BookChallengeContract.View view, Context context) {
        this.view = view;
        this.navigator = new Navigator(context);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void onStart() {
        loadCounter();
        loadBooks();
    }

    @Override
    public void onStartWitchData(Bundle bundle) {

    }

    private void loadCounter() {
        String count = sharedPreferences.getString(STORAGE_COUNTER, "0");
        view.setCounter(count);
        setProgress(count);
    }

    private void setProgress(String count) {
        int counter = Integer.parseInt(count);
        view.setProgressBar(counter);
    }

    private void loadBooks() {
        BookStorage storage = new BookStorage();
        List<Book> books = storage.getAllWithStatus(FINISH_READING);
        view.showList(books);
        setBooksCount(books);
    }

    private void setBooksCount(List<Book> books) {
        String size = String.valueOf(books.size());
        view.setBooksCount(size);
    }

    @Override
    public void onProgressChanged(int i) {
        String progress = String.valueOf(i);
        view.setProgressCounter(progress);
    }

    @Override
    public void saveCounter(String count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORAGE_COUNTER, count);
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
