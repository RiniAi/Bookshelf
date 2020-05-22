package com.example.bookshelf.features.profile;

import android.content.SharedPreferences;

import com.example.bookshelf.database.bookChallenge.BookChallenge;

import java.util.List;

public interface ProfileContract {
    interface View {
        void setCountNumberBooks(String numberBooksProgress, String numberBooksPlan, String numberBooksRead, String numberBooksQuit);

        void loadStatisticsBookChallenge(List<BookChallenge> run);

        SharedPreferences initSharedPreferences();
    }

    interface Presenter {
        void onStart();

        void openAuthentication();
    }
}
