package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

public class AboutBookPresenter implements AboutBookContract.Presenter {
    private  AboutBookContract.View view;
    private static final String EXTRA_BOOK = "book";
    private Book book;

    public AboutBookPresenter(AboutBookContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        getBook();
    }

    private void getBook() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }
    }
}
