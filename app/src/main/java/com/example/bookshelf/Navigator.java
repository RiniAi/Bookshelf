package com.example.bookshelf;

import android.content.Context;
import android.content.Intent;

import com.example.bookshelf.database.Book;
import com.example.bookshelf.features.bookabout.AboutBookActivity;
import com.example.bookshelf.features.bookchallenge.BookChallengeActivity;
import com.example.bookshelf.features.bookedit.EditBookActivity;
import com.example.bookshelf.features.bookssearch.SearchActivity;

public class Navigator {
    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void openBook(Book book) {
        Intent aboutBook = new Intent(context, AboutBookActivity.class);
        aboutBook.putExtra(AboutBookActivity.EXTRA_BOOK, book);
        context.startActivity(aboutBook);
    }

    public void editBook(Book book) {
        Intent editBook = new Intent(context, EditBookActivity.class);
        editBook.putExtra(EditBookActivity.EXTRA_BOOK, book);
        context.startActivity(editBook);
    }

    public void openMain() {
        Intent main = new Intent(context, SearchActivity.class);
        context.startActivity(main);
    }

    public void openBookChallenge() {
        Intent bookChallenge = new Intent(context, BookChallengeActivity.class);
        context.startActivity(bookChallenge);
    }

    public void openSearch() {
        Intent search = new Intent(context, SearchActivity.class);
        context.startActivity(search);
    }
}
