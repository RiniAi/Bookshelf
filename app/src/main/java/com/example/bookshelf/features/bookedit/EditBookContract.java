package com.example.bookshelf.features.bookedit;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

public interface EditBookContract {
    interface View {
        void showBook(Book book);

        void showCover(String image);

        void showBrokenCover();

        void showStatus(Book.BookStatus bookStatus);

        void updateDate(int year, int month, int dayOfMonth);

        void showButtonDelete();

        void hideButtonDelete();

        void showDate();

        void showErrorMessage();
    }

    interface Presenter {
        void onStartWithData(Bundle bundle);

        void setDate(int dayOfMonth, int month, int year);

        void insertOrUpdateBook(float rating, String status, boolean isFavorite);

        void deleteBook();
    }
}
