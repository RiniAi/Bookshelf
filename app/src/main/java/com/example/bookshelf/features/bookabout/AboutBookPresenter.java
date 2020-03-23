package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

public class AboutBookPresenter implements AboutBookContract.Presenter {
    public static final String EXTRA_BOOK = "book";
    private AboutBookContract.View view;
    private Book book;

    public AboutBookPresenter(AboutBookContract.View view) {
        this.view = view;
    }

    @Override
    public void onStartWitchData(Bundle bundle) {
        loadBook(bundle);
    }

    private void loadBook(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }

        view.showBook(book);
        loadCover();
    }

    private void loadCover() {
        String cover = book.getImageLinks();
        if (cover == null) {
            view.showBookBrokenCover();
        } else {
            view.showBookCover(cover);
        }
    }
}
