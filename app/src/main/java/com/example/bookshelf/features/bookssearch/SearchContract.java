package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.Book;

import java.util.List;

public interface SearchContract {
    interface View {
        void showBooks(List<Book> bookList);

        void showError();

        void showErrorMessage();
    }

    interface Presenter {
        void searchBook(String query);

        void openBook(Book book);

        void editBook(Book book);

        void openMain();

        void openBookChallenge();
    }
}
