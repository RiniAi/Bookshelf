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
        getBook(bundle);
    }

    private void getBook(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }

        getBookView();
        getCover();
    }

    private void getBookView() {
        view.setBookView(book.getTitle(),
                book.getAuthors(),
                book.getAverageRating(),
                book.getPublishedDate(),
                book.getPublisher(),
                String.valueOf(book.getPageCount()),
                book.getLanguage(),
                book.getDescription());
    }

    private void getCover() {
        String cover = book.getImageLinks();
        if (cover == null) {
            view.setBrokenImage();
        } else {
            view.setImage(cover);
        }
    }

    @Override
    public void onStart() {

    }
}
