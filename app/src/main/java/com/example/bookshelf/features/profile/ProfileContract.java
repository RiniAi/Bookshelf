package com.example.bookshelf.features.profile;

public interface ProfileContract {
    interface View {
        void setCountNumberBooks(String numberBooksProgress, String numberBooksPlan, String numberBooksRead, String numberBooksQuit);
    }

    interface Presenter {
        void onStart();
    }
}
