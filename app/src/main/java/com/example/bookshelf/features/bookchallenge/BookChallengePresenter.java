package com.example.bookshelf.features.bookchallenge;

import android.content.SharedPreferences;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.book.Book;
import com.example.bookshelf.database.bookChallenge.BookChallenge;
import com.example.bookshelf.features.bookabout.AboutBookFragment;
import com.example.bookshelf.usecases.InsertOrUpdateBookChallengeUseCase;
import com.example.bookshelf.usecases.SearchBookChallengeUseCase;
import com.example.bookshelf.usecases.SearchBookWithStatusUseCase;

import java.util.List;

import javax.inject.Inject;

public class BookChallengePresenter extends BasePresenter<BookChallengeContract.View> implements BookChallengeContract.Presenter {
    public static final String STORAGE_COUNTER = "counter";
    private BookChallenge bookChallenge;
    @Inject
    SearchBookWithStatusUseCase searchBookWithStatusUseCase;
    @Inject
    InsertOrUpdateBookChallengeUseCase challengeUseCase;
    @Inject
    SearchBookChallengeUseCase searchBookChallengeUseCase;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Navigator navigator;

    @Inject
    public BookChallengePresenter(SearchBookWithStatusUseCase searchBookWithStatusUseCase, InsertOrUpdateBookChallengeUseCase challengeUseCase,
                                  SearchBookChallengeUseCase searchBookChallengeUseCase, SharedPreferences sharedPreferences, Navigator navigator) {
        this.searchBookWithStatusUseCase = searchBookWithStatusUseCase;
        this.challengeUseCase = challengeUseCase;
        this.searchBookChallengeUseCase = searchBookChallengeUseCase;
        this.sharedPreferences = sharedPreferences;
        this.navigator = navigator;
    }

    @Override
    public void onStart() {
        // Replacing the constructors with the factories methods
        bookChallenge = BookChallenge.createBookChallenge();
        loadCounter();
        loadBooks();
    }

    @Override
    public void saveOrUpdateCounterAndProgress() {
        bookChallenge.setYear(2020);
        // Replacing the constructors with the factories methods
        SearchBookChallengeUseCase.Params paramsSearch = SearchBookChallengeUseCase.Params.createParams(bookChallenge);
        InsertOrUpdateBookChallengeUseCase.Params paramsInsertOrUpdate = InsertOrUpdateBookChallengeUseCase.Params.createParams(bookChallenge, bookChallenge.progress, bookChallenge.counter);
        BookChallenge dbBookChallenge = searchBookChallengeUseCase.run(paramsSearch);
        if (bookChallenge != dbBookChallenge)
            challengeUseCase.run(paramsInsertOrUpdate);
    }

    private void loadCounter() {
        String counter;
        try {
            counter = sharedPreferences.getString(STORAGE_COUNTER, "0");
            bookChallenge.setCounter(Integer.parseInt(counter));
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
        List<Book> books = searchBookWithStatusUseCase.run(Book.BookStatus.FINISH_READING);
        if (books.isEmpty()) {
            view.hideList();
        } else {
            view.showList(books);
            changeProgress(books);
        }
    }

    private void changeProgress(List<Book> books) {
        view.changeProgress(String.valueOf(books.size()));
        bookChallenge.setProgress(books.size());
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
        bookChallenge.setCounter(Integer.parseInt(counter));
    }

    @Override
    public void openBook(Book book) {
        view.openBook(AboutBookFragment.newInstance(book));
    }
}
