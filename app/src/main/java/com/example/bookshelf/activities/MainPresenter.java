package com.example.bookshelf.activities;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.Storage;
import com.example.bookshelf.room.Book;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private Intent intent;
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onItemClick(Book book) {
        intent = new Intent((Context) view, AboutBookActivity.class);
        intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        ((Context) view).startActivity(intent);
    }

    @Override
    public void onEditClick(Book book) {
        intent = new Intent((Context) view, EditBookActivity.class);
        intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
        ((Context) view).startActivity(intent);
    }

    @Override
    public void loadBooks() {
        Storage storage = new Storage();
        List<Book> booksFromDatabase = storage.searchForBooksWithStatus();
        if (booksFromDatabase.isEmpty()) {
            view.hideList();
        } else {
            view.showList();
            view.loadBooks(booksFromDatabase);
        }
    }

    @Override
    public void goToBookChallenge() {
        Intent bookChallenge = new Intent((Context) view, BookChallengeActivity.class);
        ((Context) view).startActivity(bookChallenge);
    }

    @Override
    public void goToSearchActivity() {
        Intent search = new Intent((Context) view, SearchActivity.class);
        ((Context) view).startActivity(search);
    }
}
