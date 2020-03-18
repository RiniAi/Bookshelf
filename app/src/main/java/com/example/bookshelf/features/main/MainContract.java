package com.example.bookshelf.features.main;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;
import com.example.bookshelf.database.Book;

import java.util.List;

public interface MainContract {
    interface View extends BaseView {
        void buildRecyclerView();

        void hideList();

        void showList(List<Book> books);
    }

    interface Presenter extends BasePresenter {
        void loadBooks();

        void openBook(Book book);

        void editBook(Book book);

        void openBookChallenge();

        void openSearchActivity();
    }
}
