package com.example.bookshelf;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.bookabout.AboutBookPresenter;
import com.example.bookshelf.features.bookchallenge.BookChallengeActivity;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookedit.EditBookPresenter;
import com.example.bookshelf.features.bookssearch.SearchActivity;

import javax.inject.Inject;

public class Navigator {
    @Inject
    Context context;

    @Inject
    public Navigator() {
    }

    public void openBook(Book book) {
        Intent intent = new Intent(context, AboutBookActivity.class);
        intent.putExtra(AboutBookPresenter.EXTRA_BOOK, book);
        context.startActivity(intent);
    }

    public void editBook(Book book) {
        Intent intent = new Intent(context, EditBookActivity.class);
        intent.putExtra(EditBookPresenter.EXTRA_BOOK, book);
        context.startActivity(intent);
    }

    public void openMain() {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public void openBookChallenge() {
        Intent intent = new Intent(context, BookChallengeActivity.class);
        context.startActivity(intent);
    }

    public void openSearch() {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}
