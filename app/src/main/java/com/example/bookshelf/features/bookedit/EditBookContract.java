package com.example.bookshelf.features.bookedit;

import com.example.bookshelf.base.BasePresenter;
import com.example.bookshelf.base.BaseView;
import com.example.bookshelf.database.Book;

public interface EditBookContract {
    interface View extends BaseView {

        void setBookView(String title,
                         String authors,
                         float averRating,
                         float userRating,
                         boolean isFavorite);

        void setImage(String image);

        void setBrokenImage();

        void setStatus(Book.BookStatus bookStatus);

        void updateDate(int year, int month, int dayOfMonth);

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
