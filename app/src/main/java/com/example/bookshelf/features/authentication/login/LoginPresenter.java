package com.example.bookshelf.features.authentication.login;

import android.text.TextUtils;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private FirebaseAuth firebaseAuth;

    @Inject
    Navigator navigator;

    @Inject
    public LoginPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void start(String email, String password) {
        if (email.isEmpty() || password.isEmpty() || !isValidEmail(email)) {
            view.checkDate();
            view.showView();
            checkEmail(email);
            checkPassword(password);
        } else {
            initFireBase();
            searchUserToFireBase(email, password);
        }
    }

    @Override
    public void openMain() {
        navigator.openMain();
    }

    private void checkEmail(String email) {
        if (email.isEmpty() || !isValidEmail(email)) {
            if (email.isEmpty()) {
                view.showErrorEmptyFields("email");
            } else if (!isValidEmail(email)) {
                view.showErrorInvalidFields();
            }
        } else {
            view.hintErrorFields("email");
        }
    }

    private void checkPassword(String password) {
        if (password.isEmpty()) {
            view.showErrorEmptyFields("password");
        } else {
            view.hintErrorFields("password");
        }
    }

    private void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void searchUserToFireBase(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> view.successfulLogin())
                .addOnFailureListener(exception -> view.errorLogin(exception));
    }

    private static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
