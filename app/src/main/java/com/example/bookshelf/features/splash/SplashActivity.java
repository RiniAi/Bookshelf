package com.example.bookshelf.features.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookshelf.App;
import com.example.bookshelf.R;
import com.example.bookshelf.base.BasePresenter;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashContract.View{
    @Inject
    SplashContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splach);
        App.getAppComponent().activityComponent().inject(this);
        ((BasePresenter) presenter).setView(this);
        presenter.loadDateForLoginFromSharedPreferences();
    }

    @Override
    public SharedPreferences initSharedPreferences() {
        return getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
    }

    @Override
    public void successfulLogin() {
        presenter.openMain();
    }

    @Override
    public void errorLogin(Exception exception) {

    }
}
