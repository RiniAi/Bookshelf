package com.example.bookshelf.features.bookssearch;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;
import com.example.bookshelf.database.Book;

import java.util.List;

public interface SearchContract {
    interface View extends BaseView {
        void successfulRequest(List<Book> bookList);

        void errorRequest();
    }

    interface Presenter extends BasePresenter {
        void searchBook(String query);

        void openBook(Book book);

        void editBook(Book book);

        void openMain();

        void openBookChallenge();
    }
}
