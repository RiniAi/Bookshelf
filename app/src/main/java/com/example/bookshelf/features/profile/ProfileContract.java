package com.example.bookshelf.features.profile;

import com.example.bookshelf.database.BookChallenge;

import java.util.List;

public interface ProfileContract {
    interface View {
        void setCountNumberBooks(String numberBooksProgress, String numberBooksPlan, String numberBooksRead, String numberBooksQuit);

        void loadStatisticsBookChallenge(List<BookChallenge> run);
    }

    interface Presenter {
        void onStart();
    }
}
