package com.example.bookshelf.features.splash;

import android.content.SharedPreferences;

public interface SplashContract {
    interface View {
        SharedPreferences initSharedPreferences();

        void successfulLogin();

        void errorLogin(Exception exception);
    }

    interface Presenter {
        void loadDateForLoginFromSharedPreferences();

        void openMain();}
}
