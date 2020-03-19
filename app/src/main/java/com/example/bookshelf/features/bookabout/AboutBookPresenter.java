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
    public void getBook(Bundle bundle) {
        if (bundle != null && bundle.containsKey(EXTRA_BOOK)) {
            book = (Book) bundle.getSerializable(EXTRA_BOOK);
        }

        getTitle();
        getAuthors();
        getImage();
        getRating();
        getDate();
        getPublisher();
        getPageCount();
        getLanguage();
        getDescription();
    }


    private void getTitle() {
        String title = book.getTitle();
        view.setTitle(title);
    }

    private void getAuthors() {
        String authors = book.getAuthors();
        view.setAuthors(authors);
    }

    private void getImage() {
        String image = book.getImageLinks();
        if (image == null) {
            view.setBrokenImage();
        } else {
            view.setImage(image);
        }
    }

    private void getRating() {
        float rating = book.getAverageRating();
        view.setRating(rating);
    }

    private void getDate() {
        String date = book.getPublishedDate();
        view.setPublishedDate(date);
    }

    private void getPublisher() {
        String publisher = book.getPublisher();
        view.setPublisher(publisher);
    }

    private void getPageCount() {
        String pageCount = String.valueOf(book.getPageCount());
        view.setPageCount(pageCount);
    }

    private void getLanguage() {
        String language = book.getLanguage();
        view.setLanguage(language);
    }

    private void getDescription() {
        String description = book.getDescription();
        view.setDescription(description);
    }

    @Override
    public void onStart() {

    }
}
