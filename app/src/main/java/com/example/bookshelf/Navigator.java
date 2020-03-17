package com.example.bookshelf;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.bookchallenge.BookChallengeActivity;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookssearch.SearchActivity;
import com.example.bookshelf.features.main.MainContract;

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
