package com.example.bookshelf.features.authentication.register;

public interface RegisterContract {
    interface View {
        void showErrorEmptyFields(String fields);

        void showErrorInvalidFields(String fields);

        void successfulRegistration();

        void errorRegistration(Exception exception);

        void showView();

        void errorAlreadyRegistered();
    }

    interface Presenter {
        void start(String etName, String etEmail, String etPassword, String etRepassword);

        void openMain();
    }
}
