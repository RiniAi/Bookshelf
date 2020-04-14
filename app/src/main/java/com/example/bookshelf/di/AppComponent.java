package com.example.bookshelf.di;

import com.example.bookshelf.database.LocalBookStorage;

import dagger.Component;

@Component(modules = {
        AppModule.class,
        BookDataBaseModule.class,
        PresenterModule.class,
        BookStorageModule.class,
        BookRepositoryModule.class})

public interface AppComponent {
    ActivityComponent activityComponent();

    void inject(LocalBookStorage storage);
}
