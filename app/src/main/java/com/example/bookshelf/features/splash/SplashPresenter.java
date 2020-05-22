package com.example.bookshelf.features.splash;

import android.content.SharedPreferences;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    private FirebaseAuth firebaseAuth;
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    @Inject
    Navigator navigator;

    @Inject
    public SplashPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void loadDateForLoginFromSharedPreferences() {
        SharedPreferences sharedPreferences = view.initSharedPreferences();
        String email = sharedPreferences.getString(EMAIL, "");
        String password = sharedPreferences.getString(PASSWORD, "");
        if (!(email.isEmpty()) & !(password.isEmpty())) {
            initFireBase();
            start(email, password);
        } else {
            navigator.openAuthentication();
        }
    }
    private void initFireBase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void start(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    view.successfulLogin();
                })
                .addOnFailureListener(exception -> {
                    view.errorLogin(exception);
                    openAuthentication();
                });
    }

    @Override
    public void openMain() {
        navigator.openMain();
    }

    private void openAuthentication() {
        navigator.openAuthentication();
    }
}
