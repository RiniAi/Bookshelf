package com.example.bookshelf.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bookshelf.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public App providesApp(){
        return app;
    }

    @Provides
    public Context providesContext() {
        return app.getApplicationContext();
    }

    @Provides
    SharedPreferences providesSharedPreference() {
        return app.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE);
    }
}
