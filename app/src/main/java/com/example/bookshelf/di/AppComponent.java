package com.example.bookshelf.di;

import android.content.Context;

import com.example.bookshelf.database.BookStorage;
import com.example.bookshelf.features.main.MainPresenter;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookStorageModule.class,
        NavigatorModule.class})

public interface AppComponent {
    Context context();

    void injectBookStorage (BookStorage storage);

    void injectMainPresenter(MainPresenter presenter);
}
