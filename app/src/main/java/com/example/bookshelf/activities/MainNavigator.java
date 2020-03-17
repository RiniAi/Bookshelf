package com.example.bookshelf.activities;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.room.Book;

public class MainNavigator {
    private Intent intent;
    private MainContract.View view;

    public MainNavigator(MainContract.View view) {
        this.view = view;
    }

    public void onItemClick(Book book) {
        intent = new Intent((Context) view, AboutBookActivity.class);
        intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        ((Context) view).startActivity(intent);
    }

    public void onEditClick(Book book) {
        intent = new Intent((Context) view, EditBookActivity.class);
        intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
        ((Context) view).startActivity(intent);
    }

    public void goToBookChallenge() {
        Intent bookChallenge = new Intent((Context) view, BookChallengeActivity.class);
        ((Context) view).startActivity(bookChallenge);
    }

    public void goToSearchActivity() {
        Intent search = new Intent((Context) view, SearchActivity.class);
        ((Context) view).startActivity(search);
    }
}
