package com.example.bookshelf.activities;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.room.Book;

public class Navigator {
    private Context view;

    public Navigator(MainContract.View view) {
        this.view = (Context) view;
    }

    public void openBook(Book book) {
        Intent intent = new Intent(view, AboutBookActivity.class);
        intent.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        view.startActivity(intent);
    }

    public void editBook(Book book) {
        Intent intent = new Intent(view, EditBookActivity.class);
        intent.putExtra(EditBookActivity.EXTRA_BOOK, book);
        view.startActivity(intent);
    }

    public void openBookChallenge() {
        Intent bookChallenge = new Intent(view, BookChallengeActivity.class);
        view.startActivity(bookChallenge);
    }

    public void openSearchActivity() {
        Intent search = new Intent(view, SearchActivity.class);
        view.startActivity(search);
    }
}
