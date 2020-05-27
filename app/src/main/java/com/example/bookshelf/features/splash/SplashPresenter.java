package com.example.bookshelf.features.splash;

import com.example.bookshelf.Navigator;
import com.example.bookshelf.base.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    @Inject
    Navigator navigator;

    @Inject
    public SplashPresenter(Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void start() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            openMain();
        } else {
            openAuthentication();
        }
    }

    private void openMain() {
        navigator.openMain();
    }

    private void openAuthentication() {
        navigator.openAuthentication();
    }
}
