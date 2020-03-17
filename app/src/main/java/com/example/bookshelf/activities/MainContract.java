package com.example.bookshelf.activities;

import com.example.bookshelf.BasePresenter;
import com.example.bookshelf.BaseView;
import com.example.bookshelf.room.Book;

import java.util.List;

public interface MainContract {
    interface View extends BaseView {
        void buildRecyclerView();

        void hideList();

        void showList();

        void loadBooks(List<Book> booksFromDatabase);
    }

    interface Presenter extends BasePresenter {
        void onItemClick(Book book);

        void onEditClick(Book book);

        void loadBooks();

        void goToBookChallenge();

        void goToSearchActivity();
    }
}