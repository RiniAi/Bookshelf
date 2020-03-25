package com.example.bookshelf.di;

import android.content.Context;

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
    public Context context() {
        return app.getApplicationContext();
    }
}
