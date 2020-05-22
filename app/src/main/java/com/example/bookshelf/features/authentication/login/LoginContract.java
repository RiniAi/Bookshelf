package com.example.bookshelf.features.authentication.login;

public interface LoginContract {
    interface View {
        void successfulLogin();

        void errorLogin(Exception exception);

        void showView();

        void checkDate();

        void showErrorEmptyFields(String fields);

        void showErrorInvalidFields();

        void hintErrorFields(String fields);
    }

    interface Presenter {
        void start(String email, String password);

        void openMain();
    }
}
