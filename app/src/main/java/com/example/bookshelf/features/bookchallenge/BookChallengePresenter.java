package com.example.bookshelf.features.bookchallenge;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.SeekBar;

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
    public void loadCounter() {
        String count = sharedPreferences.getString(STORAGE_COUNTER, "0");
        view.initCounter(count);
        int counter = Integer.parseInt(count);
        view.initSeekBar(counter);
    }

    @Override
    public void loadBooks() {
        BookStorage storage = new BookStorage();
        List<Book> books = storage.getAllWithStatus(FINISH_READING);
        String size = String.valueOf(books.size());
        view.showList(books, size);
    }

    @Override
    public void convertCount(SeekBar seekBar) {
        String count = String.valueOf(seekBar.getProgress());
        view.setProgress(count);
    }

    @Override
    public void saveCount(SeekBar seekBar, String count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STORAGE_COUNTER, count);
        editor.apply();
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
