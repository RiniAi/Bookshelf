package com.example.bookshelf.features.bookedit;

import android.os.Bundle;

import com.example.bookshelf.database.Book;

public interface EditBookContract {
    interface View {
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

    interface Presenter {
        void onStartWitchData(Bundle bundle);

        void setDate(int dayOfMonth, int month, int year);

        void insertOrUpdateBook(float rating, String status, boolean isFavorite);

        void deleteBook();
    }
}
