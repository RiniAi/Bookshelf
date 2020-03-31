package com.example.bookshelf.features.bookabout;

import android.os.Bundle;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.database.Book;

public class AboutBookPresenter extends BasePresenter<AboutBookContract.View> implements AboutBookContract.Presenter {
    public static final String EXTRA_BOOK = "book";
    private Book book;

    @Override
    public void onStartWithData(Bundle bundle) {
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
