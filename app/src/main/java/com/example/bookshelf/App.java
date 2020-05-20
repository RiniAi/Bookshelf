package com.example.bookshelf;

import android.app.Application;

import com.example.bookshelf.di.AppComponent;
import com.example.bookshelf.di.AppModule;
import com.example.bookshelf.di.DaggerAppComponent;

public class App extends Application {
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(App.this))
                .build();
    }
}
