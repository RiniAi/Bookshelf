package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.database.Book;

import java.util.List;

public interface SearchContract {
    interface View {
        void successfulRequest(List<Book> bookList);

        void errorRequest();
    }

    interface Presenter {
        void searchBook(String query);

        void openBook(Book book);

        void editBook(Book book);

        void openMain();

        void openBookChallenge();
    }
}
