package com.example.bookshelf;

import android.content.Context;

import com.example.bookshelf.di.AppModule;

import dagger.Component;

@Component(modules = {
        AppModule.class})

public interface AppComponent {
    Context context();

    void injectApp(App app);
}
