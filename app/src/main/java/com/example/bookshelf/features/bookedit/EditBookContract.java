package com.example.bookshelf.features.bookedit;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;
import com.example.bookshelf.database.Book;

public interface EditBookContract {
    interface View extends BaseView {
        void setTitle(String title);

        void setAuthors(String authors);

        void setImage(String image);

        void setBrokenImage();

        void setAverRating(float averRating);

        void setUserRating(float userRating);

        void setStatus(Book.BookStatus s);

        void updateDate(int year, int month, int dayOfMonth);

        void setFavorite(boolean isFavorite);

        void showDelete();

        void hideDelete();

        void getDate();
    }

    interface Presenter extends BasePresenter {
        void setDate(int dayOfMonth, int month, int year);

        void insertOrUpdateBook(float rating, String status, boolean isFavorite);

        void deleteBook();
    }
}
