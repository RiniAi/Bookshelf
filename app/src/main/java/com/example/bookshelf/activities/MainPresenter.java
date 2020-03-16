package com.example.bookshelf.activities;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.Storage;
import com.example.bookshelf.adapters.BookAdapter;
import com.example.bookshelf.room.Book;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private Intent intent;
    private MainContract.View context;
    private BookAdapter bookAdapter;

    public MainPresenter(MainContract.View context, BookAdapter bookAdapter) {
        this.context = context;
        this.bookAdapter = bookAdapter;
    }

    @Override
    public void onItemClick(Book book) {
        intent = new Intent((Context) context, AboutBookActivity.class);
        intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        ((Context) context).startActivity(intent);
    }

    @Override
    public void onEditClick(Book book) {
        intent = new Intent((Context) context, EditBookActivity.class);
        intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
        ((Context) context).startActivity(intent);
    }

    @Override
    public boolean loadBooks() {
        boolean isListEmpty = true;
        Storage storage = new Storage();
        List<Book> booksFromDatabase = storage.searchForBooksWithStatus();
        if (!booksFromDatabase.isEmpty()) {
            isListEmpty = false;
            bookAdapter.setList(booksFromDatabase);
        }
        return isListEmpty;
    }

    @Override
    public void goToBookChallenge() {
        Intent bookChallenge = new Intent((Context) context, BookChallengeActivity.class);
        ((Context) context).startActivity(bookChallenge);
    }

    @Override
    public void goToSearchActivity() {
        Intent search = new Intent((Context) context, SearchActivity.class);
        ((Context) context).startActivity(search);
    }
}
