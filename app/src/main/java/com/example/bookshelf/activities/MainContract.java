package com.example.bookshelf.activities;

import com.example.bookshelf.BasePresenter;
import com.example.bookshelf.BaseView;
import com.example.bookshelf.room.Book;

public interface MainContract {
    interface View extends BaseView {
        void buildRecyclerView();

        void loadBooks();
    }

    interface Presenter extends BasePresenter {
        void onItemClick(Book book);

        void onEditClick(Book book);

        boolean loadBooks();

        void goToBookChallenge();

        void goToSearchActivity();
    }
}
